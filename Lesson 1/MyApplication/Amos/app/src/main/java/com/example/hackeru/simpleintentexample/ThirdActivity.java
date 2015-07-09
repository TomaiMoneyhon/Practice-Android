package com.example.hackeru.simpleintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by hackeru on 06/07/2015.
 */
public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        final TextView tx = (TextView)findViewById(R.id.textView);
        Button firstActivityBtn = (Button)findViewById(R.id.button);

        String[] words = {"hello", "BYEE", "OK"};
        Random rand = new Random();
        int wordNum = rand.nextInt(3);
        tx.setText(words[wordNum]);

        firstActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int counter = 0;

                Intent toFirst = new Intent(ThirdActivity.this, MainActivity.class);
                //second.putExtra("counter", counter++);
                startActivity(toFirst);

            }
        });


    }
}
