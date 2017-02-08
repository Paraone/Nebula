package com.paraonensk.gameframework.framework.impl;

/**
 * Created by ParaOneNSK on 3/3/2016.
 */
public class Background {

    private  int bgX, bgY, speedX, width = 1800;

    public Background(int x, int y){
        bgX = x;
        bgY = y;
        speedX = -6;
    }

    public void update(){
        setBgX(getBgX() + getSpeedX());

        if (getBgX() <= -1800)
            setBgX(getBgX() + (getWidth() * 2) - 10);
    }

    public int getBgX(){ return this.bgX;}

    public void setBgX(int x){ this.bgX = x; }

    public int getBgY(){return this.bgY; }

    public void setBgY(int y){this.bgY = y; }

    public int getSpeedX(){return speedX;}
    
    public void setSpeedX(int speedX){this.speedX = speedX;}

    public int getWidth(){return this.width;}
}
