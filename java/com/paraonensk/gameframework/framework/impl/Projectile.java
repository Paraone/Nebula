package com.paraonensk.gameframework.framework.impl;

/**
 *  Created by ParaOneNSK on 3/3/2016.
 */
public class Projectile {

    private final static int MOVESPEED = 20;

    private int x, y, speedX, startX, startY, travelDistance;
    private boolean visible = false;

    public CollisionBounds bounds;

    public Projectile(int startX, int startY){
        bounds = new CollisionBounds(startX, startY, 8, 8);
        this.travelDistance = 0;
        this.x = this.startX =startX;
        this.y = this.startY =startY;
        this.speedX = MOVESPEED;
        this.visible = true;
    }

    public Projectile(){
        this.travelDistance = 0;
        this.speedX = MOVESPEED;
        this.visible = true;
    }

    public void init(int x, int y, boolean visible){
        if(bounds == null){
            bounds = new CollisionBounds(x, y, 8, 8);
        }else{
            bounds.setBounds(x, y, 8, 8);
        }
        this.speedX = MOVESPEED;
        this.travelDistance = 0;
        this.x = this.startX = x;
        this.y = this.startY = y;
        this.visible = visible;
    }

    public void update(){
        bounds.setBounds(getX(), getY(), 8, 8);
        int oldX = this.startX;
        int newX=this.x + speedX;
        this.x = newX;
        travelDistance = newX - oldX;

    }
    public int getX(){return this.x;}

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getTravelDistance() {
        return travelDistance;
    }
}
