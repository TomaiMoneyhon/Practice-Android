package com.example.hackeru.fileexamplethird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hackeru on 16/07/2015.
 */
public class HandleBitmap {

    public static void writeBitmapToInternalStorage(Context context,Bitmap bitmap,String filename) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(filename,Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            fileOutputStream.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public  static Bitmap readFileFromInternalStorage(Context context,String filename) {
        Bitmap bitmap=null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            fileInputStream.close();
        }catch (Throwable ex) {
            ex.printStackTrace();
        }
        return bitmap;
    }
}
