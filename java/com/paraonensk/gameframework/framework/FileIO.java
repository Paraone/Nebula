package com.paraonensk.gameframework.framework;

/**
 * Created by ParaOneNSK on 2/23/2016.
 */

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeAsset(String fileName) throws IOException;

    public OutputStream getOutputStream(String fileName) throws IOException;

    public FileOutputStream openFileOutputStream(String fileName)throws IOException;

    public FileInputStream getFileInputStream(String fileName)throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;

    public boolean isExternalStorageWritable();
}
