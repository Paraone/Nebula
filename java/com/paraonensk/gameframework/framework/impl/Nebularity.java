package com.paraonensk.gameframework.framework.impl;

import android.content.Context;

import com.paraonensk.gameframework.framework.Screen;

/**
 * Created by ParaOneNSK on 2/28/2016.
 */
public class Nebularity extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
