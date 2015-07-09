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
        setContentView(R.layout.second);

        TextView tx = (TextView)findViewById(R.id.textView);
        Button thirdActivityBtn = (Button)findViewById(R.id.button);

        thirdActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int counter = 0;
                Intent third = new Intent(SecondActivity.this, ThirdActivity.class);
                //second.putExtra("counter", counter++);
                startActivity(third);

            }
        });


    }
}

