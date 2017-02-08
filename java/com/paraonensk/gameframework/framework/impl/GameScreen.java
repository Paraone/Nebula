package com.paraonensk.gameframework.framework.impl;

import android.graphics.Color;

import com.paraonensk.gameframework.framework.Game;
import com.paraonensk.gameframework.framework.Graphics;
import com.paraonensk.gameframework.framework.Input;
import com.paraonensk.gameframework.framework.Pixmap;
import com.paraonensk.gameframework.framework.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ParaOneNSK on 2/29/2016.
 */
public class GameScreen extends Screen {
    enum GameState{
        Ready,
        Running,
        Paused,
        EnterName,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    Map map;
    Nebula neb;
    Background bg1, bg2;
    List<Tile> bgTiles = new ArrayList<>();

    int oldScore = 0;
    String score = "0";
    List<Input.TouchEvent> tEvents;


    public GameScreen(Game game){
        super(game);
        world = new World(game);
        neb = World.getNebula();
        bg1 = world.getBg1();
        bg2 = world.getBg2();
        map = world.getMap();

        bgTiles = map.getTileList();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        tEvents = touchEvents;
        List<Input.KeyEvent> keyEvent = game.getInput().getKeyEvents();

        drawWorld(world);
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.EnterName)
            updateEnterName(touchEvents, keyEvent);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List <Input.TouchEvent> touchEvents){
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }

    private  void updateRunning(List <Input.TouchEvent> touchEvents, float deltaTime){
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                neb.setIsShooting(false);
                if(event.x > 754
                        && event.y < 100){
                    state = GameState.Paused;
                    return;
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                neb.setIsShooting(true);
                if(inBounds(event, 0, 0, 854, 480)){
                    neb.moveTo(event.x+30, event.y-16);
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                if(inBounds(event, 0, 0, 854, 480)){
                    neb.moveTo(event.x + 30, event.y-16);
                }
            }
        }

        world.update(deltaTime);
        if(world.gameOver){
            state = GameState.GameOver;
        }
        if(oldScore != world.score){
            oldScore = world.score;
            score = ""+oldScore;
            if(Settings.soundEnabled)
                Assets.shoot.play(1);
        }
    }

    private void updatePaused(List<Input.TouchEvent> touchEvents){
        int len = touchEvents.size();
        for(int i =0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(event.x>277 && event.x <= 577){
                    if(event.y > 250 && event.y <=325){
                        if(Settings.soundEnabled)
                            Assets.shoot.play(i);
                        state = GameState.Running;
                        return;
                    }
                    if(event.y > 325 && event.y <400){
                        if(Settings.soundEnabled)
                            Assets.shoot.play(i);
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateEnterName(List<Input.TouchEvent> touchEvents, List<Input.KeyEvent> keyEvent){
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

        }
        int keys = keyEvent.size();
        for(int i = 0; i<keys; i++){

        }
    }
    private void updateGameOver(List<Input.TouchEvent> touchEvents){
        int len = touchEvents.size();
        for(int i =0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(event.x>277 && event.x <= 577){
                    if(event.y > 250 && event.y <=325){  //Replay button
                        if(Settings.soundEnabled)
                            Assets.shoot.play(i);
                        game.setScreen(new GameScreen(game));
                        return;
                    }
                    if(event.y > 325 && event.y <400){  //Quit button
                        if(Settings.soundEnabled)
                            Assets.shoot.play(i);
                        Settings.addScore(world.score);
                        Settings.save(game.getFileIO());
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {

        drawWorld(world);
        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.EnterName)
            drawEnterNameUI();
        if(state == GameState.GameOver)
            drawGameOverUI();

        neb.getShots();
    }

    private void drawWorld(World world){
        Graphics g = game.getGraphics();

        //draw background0
        
        Pixmap bgPixmap = Assets.background;
        g.drawPixmap(bgPixmap, bg1.getBgX(), bg1.getBgY());
        g.drawPixmap(bgPixmap, bg2.getBgX(), bg2.getBgY());

        //draw tiles
        int len = bgTiles.size();
        for(int i=0; i<len; i++){
            Tile t = bgTiles.get(i);
            if(t.getType()!=0)g.drawPixmap(t.getTileImage(), t.getTileX(), t.getTileY());
        }

        //draw nebula
        if(neb.state != Nebula.PlayerState.Dead){
            g.drawPixmap(neb.currentAnim.getPixmap(),neb.getX(), neb.getY());
        }

        //draw bullets
        Pixmap nebulaBullets = Assets.bullets;
        List<Projectile> bullets = neb.getShots();
        if(!bullets.isEmpty()) {
            int l = bullets.size();
            for(int i = 0; i<l; i++){
                Projectile bullet = bullets.get(i);
                g.drawPixmap(nebulaBullets, bullet.getX(), bullet.getY());
            }
        }

        // draw enemies
        Pixmap AlienPixmap = Assets.enemy;
        List<Alien> aliens = world.aliens;
        if(!aliens.isEmpty()){
            int n = aliens.size();
            for(int i=n-1; i>=0; i--){
                Alien alien = aliens.get(i);
                g.drawPixmap(AlienPixmap, alien.getX(), alien.getY());
            }
        }
    }

    private void drawReadyUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 327, 100);
        g.drawPixmap(Assets.buttons, 754, 0, 0, 300, 100, 100);//pause button
        g.drawText("Lives: " + neb.lives, 20, 40);
        g.drawText("Score: " + world.score, 200, 40);
        g.drawLine(0, 416, 854, 416, Color.WHITE);
    }

    private void drawRunningUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons, 754, 0, 0, 300, 100, 100);//pause button
        g.drawText("Lives: " + neb.lives, 20, 40);
        g.drawText("Score: " + world.score, 200, 40);
    }

    private void drawPausedUI(){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.buttons, 754, 0, 0, 300, 100, 100);
        g.drawText("Lives: " + neb.lives, 20, 40);
        g.drawText("Score: " + world.score, 200, 40);
        g.drawPixmap(Assets.paused, 177, 100);
        g.drawPixmap(Assets.pauseMenu, 277, 250);
        g.drawLine(0, 230, 854, 230, Color.WHITE);
    }

    private void drawEnterNameUI(){
        Graphics g = game.getGraphics();
        
    }

    private void drawGameOverUI(){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 177, 100);
        g.drawPixmap(Assets.gameOverMenu, 277, 250);
        g.drawPixmap(Assets.buttons, 754, 0, 0, 300, 100, 100);//pause button
        g.drawText("Lives: " + neb.lives, 20, 40);
        g.drawText("Score: "+ world.score, 200, 40);

        g.drawLine(0, 230, 854, 230, Color.WHITE);
    }

    @Override
    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }
    public Game getGame(){
        return game;
    }
}