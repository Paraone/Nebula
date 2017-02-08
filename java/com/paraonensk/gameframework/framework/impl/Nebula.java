package com.paraonensk.gameframework.framework.impl;

import com.paraonensk.gameframework.framework.Animation;
import com.paraonensk.gameframework.framework.Pixmap;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ParaOneNSK on 2/29/2016.
 */
public class Nebula{
    static final int MOVESPEED = 100;
    static final int MAXBULLETS = 40;

    enum PlayerState {
        Spawning,
        Active,
        Dying,
        Dead
    }

    private boolean isShooting = false;
    private int x = 10;
    private int y = 240-32;
    int lives = 3;
    PlayerState state;


    public Pool<Projectile> bulletPool;
    public volatile List<Projectile> bullets = new ArrayList<>();

    public CollisionBounds bounds = new CollisionBounds(x+10, y+5, 54, 22);

    //Active pix
    public Pixmap nebulaPixmap = Assets.nebula;
    public Pixmap nebula1Pixmap = Assets.nebula1;
    public Pixmap nebula2Pixmap = Assets.nebula2;
    public Pixmap nebula3Pixmap = Assets.nebula3;

    //Spawning pix
    public Pixmap nebSpawning = Assets.nebSpawning;

    public Pixmap currentPixmap;

    public Animation animActive, animSpawning, animDying, currentAnim;

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public Nebula(int x, int y){
        this.x = x;
        this.y = y;
        state = PlayerState.Spawning;
        Pool.PoolObjectFactory<Projectile> factory = new Pool.PoolObjectFactory<Projectile>() {
            @Override
            public Projectile createObject() {
                return new Projectile();
            }
        };

        bulletPool = new Pool<>(factory, MAXBULLETS);

        animSpawning = new Animation(false);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);
        animSpawning.addFrame(nebSpawning, 20);
        animSpawning.addFrame(nebulaPixmap, 20);

        animActive = new Animation(true);
        animActive.addFrame(nebulaPixmap, 50);
        animActive.addFrame(nebula1Pixmap, 50);
        animActive.addFrame(nebula2Pixmap, 50);
        animActive.addFrame(nebula3Pixmap, 50);
        animActive.addFrame(nebula2Pixmap, 50);
        animActive.addFrame(nebula1Pixmap, 50);

        animDying = new Animation(false);
        animDying.addFrame(Assets.nebexplode01, 50);
        animDying.addFrame(Assets.nebexplode02, 50);
        animDying.addFrame(Assets.nebexplode03, 50);
        animDying.addFrame(Assets.nebexplode04, 50);
        animDying.addFrame(Assets.nebexplode05, 50);
        animDying.addFrame(Assets.nebexplode06, 50);
        animDying.addFrame(Assets.nebexplode07, 50);
        animDying.addFrame(Assets.nebexplode08, 50);
        animDying.addFrame(Assets.nebexplode09, 50);
        animDying.addFrame(Assets.nebexplode10, 50);
        animDying.addFrame(Assets.nebexplode11, 50);
        animDying.addFrame(Assets.nebexplode12, 50);
        animDying.addFrame(Assets.nebexplode13, 50);
        animDying.addFrame(Assets.nebexplode14, 50);
        animDying.addFrame(Assets.nebexplode15, 50);

        currentAnim = animSpawning;
        currentPixmap = currentAnim.getPixmap();
    }

    public void update(){
        animate();
        bounds.setBounds(getX()+10, getY()+5, 54, 22);
    }

    public void animate(){
        synchronized (this){
            currentAnim.update(10);
            currentPixmap = currentAnim.getPixmap();
        }
    }

    public void shoot(){
        int len =bullets.size();
        if(isShooting() && state != Nebula.PlayerState.Dying) {
            if(len >0 ){
                Projectile bullet = bullets.get(bullets.size()-1);
                if(bullet.getTravelDistance()>60) {
                    synchronized (this){
                        Projectile b = bulletPool.newObject();
                        b.init(this.x+50, this.y+16, true);
                        bullets.add(b);
                    }
                }
            }else if(len == 0) {
                synchronized (this){
                    Projectile b = bulletPool.newObject();
                    b.init(this.x+50, this.y+16, true);
                    bullets.add(b);
                }
            }
        }

    }
    public List<Projectile> getShots(){
        return bullets;
    }
    public boolean isShooting() {
        return isShooting;
    }
    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public void moveTo(int eventX, int eventY){
        if(eventX != this.x){
            int deltaX =eventX -this.x;
            int deltaY = eventY - this.y;
            double norm = Math.sqrt((this.x + eventX)*(this.x+eventX) + (this.y +eventY)*(this.y+eventY));

            if(norm != 0){
                deltaX *= MOVESPEED/norm;
                deltaY *= MOVESPEED/norm;
            }
            this.x += deltaX;
            this.y += deltaY;

        }
    }

    public void setState(PlayerState state) {
        synchronized (this){
            currentAnim.reset(false);
            this.state = state;
            if(state == PlayerState.Active){
                currentAnim = animActive;
            }
            if(state == PlayerState.Dying){
                currentAnim = animDying;
            }
            if(state == PlayerState.Spawning){
                currentAnim = animSpawning;
            }
            currentPixmap = currentAnim.getPixmap();
        }

    }
}
