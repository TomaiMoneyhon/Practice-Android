package com.example.hackeru.sharedexampleadv;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by hackeru on 13/07/2015.
 */
public class SecondActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView tv = (TextView)findViewById(R.id.full_name_tv);

        SharedPreferences sp = getSharedPreferences(MainActivity.MY_SHARED,Activity.MODE_PRIVATE);

        tv.setText(sp.getString("fname","") + " " + sp.getString("lname",""));
    }
}
