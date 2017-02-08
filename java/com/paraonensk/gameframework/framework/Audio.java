package com.paraonensk.gameframework.framework;

import java.io.IOException;

/**
 * Created by ParaOneNSK on 2/23/2016.
 */
public interface Audio {
    public Music newMusic(String filename) throws IOException;

    public Sound newSound(String filename) throws IOException;
}
