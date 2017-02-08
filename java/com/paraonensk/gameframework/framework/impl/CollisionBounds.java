package com.paraonensk.gameframework.framework.impl;

import android.graphics.Rect;

/**
 *  Created by ParaOneNSK on 3/15/2016.
 */
public class CollisionBounds {
    int x1, y1, x2, y2, width, height;
    Rect rect;

    public CollisionBounds(int x1, int y1, int width, int height){
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        rect = new Rect(x1, y1, width, height);
    }

    public void setBounds(int x, int y, int width, int height){
        this.x1 = x;
        this.y1 = y;
        this.width = width;
        this.height = height;
        this.x2 = x + width;
        this.y2 = y + height;
        this.rect.set(x, y, width, height);
    }

    public boolean collidesWith(CollisionBounds bounds){
        return !(bounds.x2 < this.x1) && !(this.x2 < bounds.x1) && !(bounds.y2 < this.y1) && !(this.y2 < bounds.y1);
    }
}
