package com.paraonensk.gameframework.framework.impl;

import com.paraonensk.gameframework.framework.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Created by ParaOneNSK on 2/29/2016.
 */
public class World {
    static final int WORLD_WIDTH = 854;
    static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 10;

    Game game;

    private static Nebula nebula;
    private Background bg1, bg2;
    private Map map;

    List<Tile> tileList = new ArrayList<>();

    public boolean gameOver = false;
    public int score = 0;
    public int killcount = 0;

    Pool<Alien> alienPool;
    List<Alien> aliens = new ArrayList<>();

    Random random = new Random();

    public World(Game game) {

        //initialize the world here
        this.game = game;
        bg1 = new Background(0, 0);
        bg2 = new Background(1800, 0);
        nebula = new Nebula(10, 208);
        map = new Map(game, "data/map1.txt");
        tileList = map.tileList;

        //crate alienFactory
        Pool.PoolObjectFactory<Alien> alienFactory = new Pool.PoolObjectFactory<Alien>() {
            @Override
            public Alien createObject() {
                return new Alien(920, random.nextInt(450));
            }
        };
        alienPool = new Pool<>(alienFactory, 5);
        if(aliens.isEmpty()){
            int len = 4;
            synchronized (this){
                for(int i=len; i>0; i--){
                    Alien a = alienPool.newObject();
                    int ran = random.nextInt(6)*-1;
                    ran+=-1;
                    a.init(920, random.nextInt(420), ran, true);
                    aliens.add(a);
                }
            }
        }
    }

    public static Nebula getNebula() {
        return nebula;
    }

    public Map getMap(){return map;}
    public Background getBg1() {
        return bg1;
    }

    public Background getBg2() {
        return bg2;
    }

    public void update(float deltaTime) {
        if (gameOver)
            return;
        if(killcount == 20){
            if(bg1.getSpeedX() > -16){
                bg1.setSpeedX(bg1.getSpeedX()-1);
                bg2.setSpeedX(bg2.getSpeedX()-1);
                setMapSpeed(map.speedX -2);
            }
            killcount = 0;
        }

        //scroll background
        bg1.update();
        bg2.update();

        if(nebula.animDying.isEnded()) {
            if(nebula.lives < 1){
                nebula.setState(Nebula.PlayerState.Dead);
                gameOver = true;
                return;
            }
            else
                nebula.setState(Nebula.PlayerState.Spawning);
        }
        // update nebula.animActive

        if(nebula.animSpawning.isEnded()) {
            nebula.setState(Nebula.PlayerState.Active);
        }
        nebula.update();

        //update map
        updateMap();

        //shoot and update bullets
        synchronized (this){
            int len = nebula.bullets.size();
            nebula.shoot();
            for(int i =len-1; i>=0; i--){
                Projectile bullet = nebula.bullets.get(i);
                if(bullet.isVisible()){
                    bullet.update();
                    if(bullet.getX() > 854)
                        bullet.setVisible(false);
                    if(bullet.getX() < 855){
                        int tSize = tileList.size();
                        int aSize = aliens.size();
                        for(int m= aSize -1; m>=0; m--){
                            Enemy a = aliens.get(m);
                            if(bullet.bounds.collidesWith(a.bounds)){
                                bullet.setVisible(false);
                                a.setVisible(false);
                                score += SCORE_INCREMENT;
                                killcount++;
                            }
                        }
                        for(int x=0; x<tSize; x++){
                            Tile t = tileList.get(x);
                            if(t.getTileX()>0 && t.getTileX()<855 && t.getType()>0){
                                if(bullet.bounds.collidesWith(t.bounds)){
                                    bullet.setVisible(false);
                                }
                            }
                        }
                    }
                }else{
                    nebula.bulletPool.free(bullet);
                    nebula.bullets.remove(bullet);
                }
            }
        }

        // update enemies
        int len = aliens.size();
        if(len == 0) {
            len = random.nextInt(4);
            len +=1;
            synchronized (this){
                for(int i=len-1; i>=0; i--) spawnAliens();
            }
        }
        synchronized (this){
            for(int i=len-1; i>=0; i--){
                Alien alien = aliens.get(i);
                if(alien.isVisible()){
                    alien.update();
                    if(alien.bounds.collidesWith(nebula.bounds) && nebula.state == Nebula.PlayerState.Active){
                        nebula.setState(Nebula.PlayerState.Dying);
                        nebula.lives--;
                    }
                    if(alien.getX() < -30) alien.setVisible(false);
                }else{
                    alienPool.free(alien);
                    aliens.remove(i);
                }
            }
        }
    }
    private void spawnAliens(){
        Alien a = alienPool.newObject();
        int ran =(random.nextInt(6)*-1);
        ran += -1;
        a.init(920, random.nextInt(420), ran, true);
        aliens.add(a);
    }
    public void setMapSpeed(int speedX){
        int len = tileList.size();
        synchronized (this){
            for(int i =len-1; i>=0; i--){
                Tile t = tileList.get(i);
                t.setSpeedX(speedX);
            }
            map.speedX = speedX;
        }
    }
    public void updateMap(){
        int len = tileList.size();
        for(int i = 0; i<len; i++){
            Tile t = tileList.get(i);
            t.update();

            if(t.getType()>0){
                if(t.bounds.collidesWith(nebula.bounds) && nebula.state == Nebula.PlayerState.Active){
                    nebula.setState(Nebula.PlayerState.Dying);
                    nebula.lives--;
                }
            }
            if(t.getTileX()<=-40){
                t.setTileX(t.getTileX()+map.mapWidth+2000);
            }
        }
    }
}
