package com.paraonensk.gameframework.framework.impl;

import android.os.Environment;

import com.paraonensk.gameframework.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ParaOneNSK on 2/28/2016.
 */
public class Settings {
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[]{1800, 1550, 1200, 900, 750};

    public static void load(FileIO files){
        BufferedReader reader = null;
        try{
            FileInputStream fInStream= files.getFileInputStream(".nebula");
            InputStreamReader inStreamReader = new InputStreamReader(fInStream);
            reader = new BufferedReader(inStreamReader);
            soundEnabled = Boolean.parseBoolean(reader.readLine());
            for(int i=0; i<5; i++){
                highscores[i] = Integer.parseInt(reader.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }finally{
            try{
                if(reader != null)
                    reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter writer = null;
        try{
            FileOutputStream fOutStream = files.openFileOutputStream(".nebula");
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(fOutStream);
            writer = new BufferedWriter(outStreamWriter);
            writer.write(Boolean.toString(soundEnabled)+"\n");
            for (int i = 0; i < 5; i++) {
                writer.write(Integer.toString(highscores[i])+"\n");
            }
        }catch(IOException e){
        }finally{
            try{
                if(writer != null)
                    writer.close();
            }catch(IOException e){
            }
        }
    }

    public static void addScore(int score){
        for(int i = 0; i<5; i++){
            if(highscores[i] < score){
                for(int j = 4; j>i; j--){
                    highscores[j] = highscores[j -1];
                }
                highscores[i] = score;
                break;
            }
        }
    }
}
