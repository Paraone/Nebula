package com.paraonensk.gameframework.framework.impl;

/**
 * Created by ParaOneNSK on 3/9/2016.
 */
public class Enemy {
    private static int MOVESPEED = -5;
    private int maxHealth,
            currentHealth,
            power, speedX,
            x, y;

    public Enemy(){
        speedX = MOVESPEED;
        bounds = new CollisionBounds(this.x, this.y, 64, 32);
    }

    public void update(){
        x += speedX;
        bounds.setBounds(getX(), getY(), 64, 32);
    }

    public CollisionBounds bounds;

    private boolean Visible = false;

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        Visible = visible;
    }



    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
