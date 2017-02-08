package com.paraonensk.gameframework.framework.impl;

import android.view.MotionEvent;
import android.view.View;

import com.paraonensk.gameframework.framework.Input;
import com.paraonensk.gameframework.framework.Input.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ParaOneNSK on 2/27/2016.
 */

public class MultiTouchHandler implements TouchHandler{
    private static final int MAX_TOUCHPOINTS = 10;
    boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
    int[] touchX = new int[MAX_TOUCHPOINTS];
    int[] touchY = new int[MAX_TOUCHPOINTS];
    int[] id = new int[MAX_TOUCHPOINTS];

    Pool<TouchEvent> touchPool;
    List<TouchEvent> touchEventsBuffer = new ArrayList<>();
    List<TouchEvent> touchEvents = new ArrayList<>();
    float scaleX;
    float scaleY;

    public MultiTouchHandler(View view,float scaleX, float scaleY){
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        touchPool = new Pool<>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this){
            int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex =(motionEvent.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                    >>MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerCount = motionEvent.getPointerCount();
            TouchEvent touchEvent;
            for(int i = 0; i<MAX_TOUCHPOINTS; i++){
                if(i>=pointerCount){
                    isTouched[i] = false;
                    id[i] = -1;
                    continue;
                }
                if(motionEvent.getAction() != MotionEvent.ACTION_MOVE &&
                        i != pointerIndex){
                    //if it's an up,down,cancel, out event, mask the id to see if we should process it for this touch point
                    continue;
                }
                int pointerId = motionEvent.getPointerId(i);

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        isTouched[i]=true;
                        id[i] = pointerId;
                        touchX[i] = (int)(motionEvent.getX(i)*scaleX);
                        touchY[i] = (int)(motionEvent.getY(i)*scaleY);
                        touchEvent = touchPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = (int)(motionEvent.getX(i) * scaleX);
                        touchEvent.y = (int)(motionEvent.getY(i)* scaleY);
                        touchEventsBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        isTouched[i] = false;
                        id[i] = pointerId;
                        touchX[i] = (int)(motionEvent.getX(i)*scaleX);
                        touchEvent = touchPool.newObject();
                        touchEvent.type = TouchEvent.TOUCH_UP;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = (int)(motionEvent.getX(i)*scaleX);
                        touchEvent.y = touchY[i] = (int)(motionEvent.getY()*scaleY);
                        touchEventsBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchX[i] = (int)(motionEvent.getX(i)*scaleX);
                        touchY[i] = (int)(motionEvent.getY(i)*scaleY);
                        touchEvent = touchPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = (int)(motionEvent.getX(i)*scaleX);
                        touchEvent.y = (int)(motionEvent.getY(i)*scaleY);
                        touchEventsBuffer.add(touchEvent);
                        break;
                }
            }
            return true;
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized(this) {
            int index = getIndex(pointer);
            return !(index < 0 || index >= MAX_TOUCHPOINTS) && isTouched[index];
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index <0 || index>=MAX_TOUCHPOINTS)
                return 0;
            else
                return touchX[index];
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index <0 || index >=MAX_TOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len = touchEvents.size();
            for (int i = 0; i<len; i++)
                touchPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    //returns the index for a given pointerID or =1 if no index
    private int getIndex(int pointerID){
        for (int i = 0;i<MAX_TOUCHPOINTS; i++){
            if (id[i] == pointerID){
                return i;
            }
        }

        return -1;
    }
}