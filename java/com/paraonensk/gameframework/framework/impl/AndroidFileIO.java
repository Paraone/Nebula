package com.paraonensk.gameframework.framework.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.paraonensk.gameframework.framework.FileIO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ParaOneNSK on 2/26/2016.
 */
public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(Context context){
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory()
            .getAbsolutePath();
    }

    public InputStream readAsset(String fileName) throws IOException{
        return assets.open(fileName);
    }

    public InputStream readFile(String fileName) throws IOException{
        return new FileInputStream(externalStoragePath+fileName);
    }

    public OutputStream writeAsset(String fileName) throws IOException{
        return new FileOutputStream(fileName);
    }

    public OutputStream writeFile(String fileName)throws IOException{
        return new FileOutputStream(externalStoragePath+fileName);
    }

    public OutputStream getOutputStream(String fileName)throws IOException{
        OutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        return outputStream;
    }

    @Override
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }else
            return false;
    }

    @Override
    public FileOutputStream openFileOutputStream(String fileName)throws IOException{
        FileOutputStream fo = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        return fo;
    }

    @Override
    public FileInputStream getFileInputStream(String fileName) throws IOException {
        FileInputStream inputStream = context.openFileInput(fileName);
        return inputStream;
    }

    public SharedPreferences getPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}