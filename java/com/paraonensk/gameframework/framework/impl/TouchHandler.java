package com.paraonensk.gameframework.framework.impl;

import android.view.View;

import com.paraonensk.gameframework.framework.Input;

import java.util.List;

/**
 *  Created by ParaOneNSK on 2/28/2016.
 */
public interface TouchHandler extends View.OnTouchListener {
    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);

    int getTouchY(int pointer);

    List<Input.TouchEvent> getTouchEvents();
}
