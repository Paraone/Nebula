package com.paraonensk.gameframework.framework.impl;

import  java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.paraonensk.gameframework.framework.Input.KeyEvent;
import com.paraonensk.gameframework.framework.impl.Pool.PoolObjectFactory;

public class KeyboardHandler implements OnKeyListener{
    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent>keyEventPool;
    List<KeyEvent>keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent>keyEvents = new ArrayList<KeyEvent>();

    public KeyboardHandler(View view){
        PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        keyEventPool = new Pool<KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    public boolean onKey(View view, int keyCode, android.view.KeyEvent keyEvent) {
        if(keyEvent.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;

        synchronized (this){
            KeyEvent keyEvent1 = keyEventPool.newObject();
            keyEvent1.keyCode = keyCode;
            keyEvent1.keyChar = (char) keyEvent.getUnicodeChar();
            if(keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN){
                keyEvent1.type = KeyEvent.KEY_DOWN;
                if(keyCode> 0 && keyCode<127)
                    pressedKeys[keyCode] = true;
            }
            if(keyEvent.getAction() == android.view.KeyEvent.ACTION_UP){
                keyEvent1.type = KeyEvent.KEY_UP;
                if(keyCode>0 && keyCode<127)
                    pressedKeys[keyCode] = false;
            }
            keyEventsBuffer.add(keyEvent1);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode){
        if(keyCode<0 || keyCode>127)
            return false;
        return pressedKeys[keyCode];
    }

    public List<KeyEvent> getKeyEvents(){
        synchronized (this){
            int len = keyEvents.size();
            for(int i = 0; i<len;i++){
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}