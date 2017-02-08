package com.paraonensk.gameframework.framework.impl;

/**
 * Created by ParaOneNSK on 3/9/2016.
 */
public class Alien extends Enemy {

    public Alien(int x, int y){
        setX(x);
        setY(y);
    }

    public void init(int x, int y, int speedX, boolean visible){
        setX(x);
        setY(y);
        setSpeedX(speedX);
        setVisible(visible);
    }
}
