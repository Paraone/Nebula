package com.paraonensk.gameframework.framework.impl;

import android.graphics.Color;
import android.graphics.Paint;

import com.paraonensk.gameframework.framework.Game;
import com.paraonensk.gameframework.framework.Graphics;
import com.paraonensk.gameframework.framework.Input;
import com.paraonensk.gameframework.framework.Screen;

import java.util.List;

/**
 *  Created by ParaOneNSK on 2/28/2016.
 */
public class HighscoreScreen extends Screen {

    public HighscoreScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(event.x>754 && event.y <100){
                    if(Settings.soundEnabled)
                        Assets.shoot.play(i);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.highscores, 202, 50);
        g.drawPixmap(Assets.buttons, 754, 0, 0, 400, 100, 100);
        for(int i =Settings.highscores.length-1; i>=0; i--){
            g.drawText((i+1)+". "+Settings.highscores[i], 280, 175+(i*60), Color.WHITE, 35, Paint.Align.LEFT);
        }

        //g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
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
