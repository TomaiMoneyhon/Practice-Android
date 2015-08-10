package com.example.hackeru.notificationexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by hackeru on 10/08/2015.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView textView = (TextView)findViewById(R.id.textview);
        textView.setText(getIntent().getStringExtra("key"));

    }
}
