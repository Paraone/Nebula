package com.paraonensk.gameframework.framework.impl;

import android.graphics.Rect;

import com.paraonensk.gameframework.framework.Pixmap;

/**
 *  Created by ParaOneNSK on 3/11/2016.
 */
public class Tile {
    private int tileX, tileY, speedX, type;

    public Rect r;
    public CollisionBounds bounds;
    public Pixmap tileImage;

    public Tile(int x, int y, int typeInt, int speedX){
        tileX = x*40;
        tileY = y*40;

        type = typeInt;
        this.speedX = speedX;

        bounds = new CollisionBounds(x, y, 40, 40);
        r = new Rect(x, y, 40, 40);

        if      (type == 1){
            tileImage = Assets.tile1;
        }else if(type == 2){
            tileImage = Assets.tile2;
        }else if(type == 3){
            tileImage = Assets.tile3;
        }else if(type == 4){
            tileImage = Assets.tile4;
        }else if(type == 5){
            tileImage = Assets.tile5;
        }else if(type == 6){
            tileImage = Assets.tile6;
        }else if(type == 7){
            tileImage = Assets.tile7;
        }else if(type == 8){
            tileImage = Assets.tile8;
        }else if(type == 9){
            tileImage = Assets.tile9;
        }else
            this.type = 0;
    }

    public Tile(){

    }

    public void init(int x, int y, int type, int speedX){
        tileX = x* 40;
        tileY = y* 40;

        this.type = type;
        r = new Rect(x, y, 40, 40);

        this.speedX = speedX;
        if      (type == 1){
            tileImage = Assets.tile1;
        }else if(type == 2){
            tileImage = Assets.tile2;
        }else if(type == 3){
            tileImage = Assets.tile3;
        }else if(type == 4){
            tileImage = Assets.tile4;
        }else if(type == 5){
            tileImage = Assets.tile5;
        }else if(type == 6){
            tileImage = Assets.tile6;
        }else if(type == 7){
            tileImage = Assets.tile7;
        }else if(type == 8){
            tileImage = Assets.tile8;
        }else if(type == 9){
            tileImage = Assets.tile9;
        }else{
            this.type = 0;
        }
    }

    public void update(){
        tileX += speedX;
        bounds.setBounds(getTileX(), getTileY(), 40, 40);
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Pixmap getTileImage() {
        return tileImage;
    }

    public void setTileImage(Pixmap tileImage) {
        this.tileImage = tileImage;
    }

}
