package com.paraonensk.gameframework.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.paraonensk.gameframework.framework.Audio;
import com.paraonensk.gameframework.framework.Music;
import com.paraonensk.gameframework.framework.Sound;

import java.io.IOException;

/**
 * Created by ParaOneNSK on 2/27/2016.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    public Music newMusic(String filename){
        try{
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetFileDescriptor);
        }catch(IOException e){
            throw new RuntimeException("Couldn't load music file '" +filename+"'");
        }
    }

    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            int soundID = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool, soundID);
        }catch(IOException e){
            throw new RuntimeException("Couldn't load sound file '" +filename+"'");
        }
    }
}