package com.example.hackeru.sharedexampleadv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hackeru on 13/07/2015.
 */
public class HandleSharedPrefs {

    public static String readString(Context context,String fileName,String key) {
        SharedPreferences sp  = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return  sp.getString(key,"");
    }

    public static boolean writeString(Context context,String fileName,String key,String value) {
        SharedPreferences sp  = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        return editor.commit();
    }
}
