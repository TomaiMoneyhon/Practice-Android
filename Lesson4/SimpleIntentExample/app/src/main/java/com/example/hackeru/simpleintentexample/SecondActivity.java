package com.example.hackeru.simpleintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hackeru on 06/07/2015.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Intent i = getIntent();
        String str = i.getStringExtra("edit_text_value");
        TextView tv = (TextView)findViewById(R.id.text_view);
        tv.setText(str);

        Button btn = (Button)findViewById(R.id.first_activity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
