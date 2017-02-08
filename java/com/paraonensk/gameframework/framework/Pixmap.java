package com.paraonensk.gameframework.framework;

/**
 * Created by ParaOneNSK on 2/23/2016.
 */

import com.paraonensk.gameframework.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public int getStartX();

    public int getStartY();

    public PixmapFormat getFormat();

    public void dispose();
}
