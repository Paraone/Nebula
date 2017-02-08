package com.paraonensk.gameframework.framework.impl;


import android.graphics.Rect;

import com.paraonensk.gameframework.framework.FileIO;
import com.paraonensk.gameframework.framework.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ParaOneNSK on 3/13/2016.
 */
public class Map {
    StringBuilder stringBuilder;
    List<Tile> tileList;
    Game game;
    FileIO fileIO;
    int mapWidth;
    int speedX = -10;

    public Map(Game game,String filename){
        this.game = game;
        tileList = new ArrayList<>();
        fileIO = game.getFileIO();
        try{
            loadMap(filename);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void loadMap(String filename) throws IOException{
        List lines = new ArrayList();
        int width = 0;
        int height = 0;

        stringBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileIO.readAsset(filename)));
        while(true){
            String line = reader.readLine();
            //if no more lines to read
            if(line == null){
                reader.close();
                break;
            }
            if(!line.startsWith("!")){
                lines.add(line);
                System.out.println(line);
                width = Math.max(width, line.length());
                mapWidth = width*40;
            }
        }
        height = lines.size();
        for(int j = 0; j<12; j++){
            String line = (String)lines.get(j);
            for(int i=0; i<width; i++){
                if(i<line.length()){
                    char ch = line.charAt(i);
                    int type = Character.getNumericValue(ch);
                    System.out.println("i=" + i + " j=" + j + " ch=" + type);
                    Tile tile = new Tile(i, j, type, speedX);
                    tileList.add(tile);
                }
            }
        }
    }

    public List<Tile> getTileList() {
        return tileList;
    }
    public void setSpeedX(int speedX){
        int len = tileList.size();
        for(int i = 0; i<len; i++){
            Tile t = tileList.get(i);
            t.setSpeedX(speedX);
        }
        this.speedX = speedX;
    }
    public void dispose(){
        fileIO = null;
        this.game = null;
        tileList = null;
    }
}
