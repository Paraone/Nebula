package com.paraonensk.gameframework.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ParaOneNSK on 3/10/2016.
 */
public class Animation {
    private boolean looping;
    private boolean ended = false;

    private List<AnimFrame> frames;
    private int currentFrame;
    private long animTime;
    private long totalDuration;

    public Animation(boolean looping){
        this.looping = looping;
        frames = new ArrayList<>();
        totalDuration = 0;
        setEnded(false);

        synchronized (this){
            animTime = 0;
            currentFrame = 0;
        }
    }

    public void reset(boolean ended){
        synchronized (this){
            totalDuration = 0;
            for(int i = 0; i<frames.size()-1; i++) totalDuration += frames.get(i).endTime;
            setEnded(ended);
            animTime = 0;
            currentFrame = 0;
        }
    }


    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public synchronized void addFrame(Pixmap pixmap, long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(pixmap, totalDuration));
    }

    public synchronized void update(long elapsedTime){
        if(frames.size()>1){
            animTime += elapsedTime;
            if(animTime>=totalDuration){
                animTime = animTime%totalDuration;
                if(!isLooping()) {
                    ended = true;
                    currentFrame = frames.size()-1;
                }else
                    currentFrame = 0;
            }

            while(animTime >= getFrame(currentFrame).endTime){
                if(!ended) currentFrame++;
                if(currentFrame >= frames.size()) {
                   if(looping)reset(false);
                    else reset(true);
                }
            }
        }
    }

    public synchronized Pixmap getPixmap(){
        if(frames.size() == 0){
            return null;
        }else{
            return getFrame(currentFrame).pixmap;
        }
    }

    private AnimFrame getFrame(int i){
        return(AnimFrame)frames.get(i);
    }

    private class AnimFrame{
        Pixmap pixmap;
        long endTime;

        public AnimFrame(Pixmap pixmap, long endTime){
            this.pixmap = pixmap;
            this.endTime = endTime;
        }
    }

    public List<AnimFrame> getFrames() {
        return frames;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
