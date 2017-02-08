package com.paraonensk.gameframework.framework.impl;

import com.paraonensk.gameframework.framework.Game;
import com.paraonensk.gameframework.framework.Graphics;
import com.paraonensk.gameframework.framework.Screen;

import java.io.IOException;

/**
 *   Created by ParaOneNSK on 2/28/2016.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        //nebula assets
        Assets.nebula = g.newPixmap("images/nebula.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebula1 = g.newPixmap("images/nebula1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebula2 = g.newPixmap("images/nebula2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebula3 = g.newPixmap("images/nebula3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode01 = g.newPixmap("images/nebulaexplode01.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode02 = g.newPixmap("images/nebulaexplode02.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode03 = g.newPixmap("images/nebulaexplode03.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode04 = g.newPixmap("images/nebulaexplode04.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode05 = g.newPixmap("images/nebulaexplode05.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode06 = g.newPixmap("images/nebulaexplode06.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode07 = g.newPixmap("images/nebulaexplode07.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode08 = g.newPixmap("images/nebulaexplode08.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode09 = g.newPixmap("images/nebulaexplode09.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode10 = g.newPixmap("images/nebulaexplode10.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode11 = g.newPixmap("images/nebulaexplode11.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode12 = g.newPixmap("images/nebulaexplode12.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode13 = g.newPixmap("images/nebulaexplode13.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode14 = g.newPixmap("images/nebulaexplode14.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebexplode15 = g.newPixmap("images/nebulaexplode15.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebSpawning = g.newPixmap("images/nebulaspawning.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nebSpawnEnd = g.newPixmap("images/nebulaspawnend.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile1 = g.newPixmap("images/tile1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile2 = g.newPixmap("images/tile2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile3 = g.newPixmap("images/tile3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile4 = g.newPixmap("images/tile4.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile5 = g.newPixmap("images/tile5.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile6 = g.newPixmap("images/tile6.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile7 = g.newPixmap("images/tile7.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile8 = g.newPixmap("images/tile8.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tile9 = g.newPixmap("images/tile9.png", Graphics.PixmapFormat.ARGB4444);
        Assets.startile = g.newPixmap("images/startile.png", Graphics.PixmapFormat.ARGB4444);
        Assets.spacetile = g.newPixmap("images/spacetile.png", Graphics.PixmapFormat.ARGB4444);
        Assets.enemy = g.newPixmap("images/enemy2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.bullets = g.newPixmap("images/bullets.png", Graphics.PixmapFormat.ARGB4444);
        // UI assets
        Assets.background = g.newPixmap("images/background.png", Graphics.PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("images/uilogo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.mainmenu = g.newPixmap("images/uimainmenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.helpscreen = g.newPixmap("images/uicontrols.png", Graphics.PixmapFormat.ARGB4444);
        Assets.highscores = g.newPixmap("images/uihighscores.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("images/uigameover.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("images/uiready.png", Graphics.PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("images/uibuttons.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameOverMenu = g.newPixmap("images/uigameovermenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pauseMenu = g.newPixmap("images/uipausemenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.paused = g.newPixmap("images/uipaused.png", Graphics.PixmapFormat.ARGB4444);
        //Assets.moveDown = g.newPixmap("movedown.png", Graphics.PixmapFormat.ARGB4444);
        //Assets.moveUp = g.newPixmap("moveup.png", Graphics.PixmapFormat.ARGB4444);
        try {
            Assets.shoot = game.getAudio().newSound("soundfx/shoot.ogg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Assets.hit = game.getAudio().newSound("soundfx/hit.ogg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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