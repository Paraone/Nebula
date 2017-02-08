package com.paraonensk.gameframework.framework.impl;

import android.graphics.Color;
import android.graphics.Paint;

import com.paraonensk.gameframework.framework.Game;
import com.paraonensk.gameframework.framework.Graphics;
import com.paraonensk.gameframework.framework.Input;
import com.paraonensk.gameframework.framework.Pixmap;
import com.paraonensk.gameframework.framework.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ParaOneNSK on 2/28/2016.
 */
public class HelpScreen extends Screen {
    public HelpScreen(Game game){
        super(game);
    }

    String controls1= "Swipe the screen to shoot and fly.";
    String controls2= "Avoid oncoming terrain. ";
    String controls3= "Shoot enemies for points.";
    Nebula nebula = new Nebula(30, 240);
    List<Projectile> bullets = new ArrayList<>();
    Background bg1 = new Background(0,0);
    Background bg2 = new Background(1800, 0);

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                nebula.setIsShooting(false);
                if(event.x > 754
                        && event.y < 100){
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                if(inBounds(event, 0, 0, 854, 480)){
                    nebula.moveTo(event.x + 30, event.y - 16);
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                if(inBounds(event, 0, 0, 854, 480)){
                    nebula.moveTo(event.x + 30, event.y - 16);
                }
            }
        }
        bg1.update();
        bg2.update();
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        drawWorld();
        g.drawPixmap(Assets.helpscreen, 202, 50);
        g.drawPixmap(Assets.buttons, 754, 0, 0, 400, 100, 100);
    }

    private void drawWorld(){
        Graphics g = game.getGraphics();

        //draw background0

        Pixmap bgPixmap = Assets.background;
        g.drawPixmap(bgPixmap, bg1.getBgX(), bg1.getBgY());
        g.drawPixmap(bgPixmap, bg2.getBgX(), bg2.getBgY());

        g.drawText(controls1, 854 / 2, 200, Color.WHITE, 40, Paint.Align.CENTER);
        g.drawText(controls2, 854 / 2, 275, Color.WHITE, 40, Paint.Align.CENTER);
        g.drawText(controls3, 854 / 2, 350, Color.WHITE, 40, Paint.Align.CENTER);

        //draw nebula
        g.drawPixmap(nebula.currentAnim.getPixmap(), nebula.getX(), nebula.getY());

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
