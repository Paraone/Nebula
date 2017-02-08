package com.paraonensk.gameframework.framework.impl;

import com.paraonensk.gameframework.framework.Game;
import com.paraonensk.gameframework.framework.Graphics;
import com.paraonensk.gameframework.framework.Input;
import com.paraonensk.gameframework.framework.Screen;

import java.util.List;

/**
 *  Created by ParaOneNSK on 2/28/2016.
 */
public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game){
        super(game);
    }
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(inBounds(event, 0, 0,100, 100)){
                    Settings.soundEnabled = !Settings.soundEnabled;
                    Settings.save(game.getFileIO());
                    if(Settings.soundEnabled)
                        Assets.shoot.play(i);
                }
                if(inBounds(event, 277, 220, 300, 100)){
                    game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                        Assets.shoot.play(i);
                    return;
                }
                if(inBounds(event, 277, 220 + 100, 300, 100)){
                    game.setScreen(new HighscoreScreen(game));
                    if(Settings.soundEnabled)
                        Assets.shoot.play(i);
                    return;
                }
                if(inBounds(event, 277, 220 + 200, 300, 100)){
                    game.setScreen(new HelpScreen(game));
                    if(Settings.soundEnabled)
                        Assets.hit.play(i);
                    return;
                }
            }
        }


    }


    @Override
    public void present(float deltaTime){
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 102, 50);
        g.drawPixmap(Assets.mainmenu, 277, 240);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 0, 0, 0, 100, 100);//sound enabled button
        else
            g.drawPixmap(Assets.buttons, 0,0, 0, 100, 100, 100);//sound disabled button
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
