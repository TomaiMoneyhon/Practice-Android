package com.example.hackeru.actionviewintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by hackeru on 09/07/2015.
 */
public class MyMail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_mail);
        TextView tv = (TextView)findViewById(R.id.text_body);
        tv.setText(getIntent().getStringExtra(Intent.EXTRA_SUBJECT));
    }
}
