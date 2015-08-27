package com.example.hackeru.animationsexample;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.animation_image_view);
        AnimationDrawable anim = (AnimationDrawable)imageView.getDrawable();
        anim.start();

        Animation tween = AnimationUtils.loadAnimation(this,R.anim.mytween);
        ImageView imageView1 = (ImageView)findViewById(R.id.launcher_iv);
        imageView1.startAnimation(tween);

        Button button = (Button)findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView)findViewById(R.id.textview1);
                Animation androidAnim = AnimationUtils.loadAnimation(MainActivity.this,
                        android.R.anim.slide_in_left | android.R.anim.fade_in);
                textView.startAnimation(androidAnim);
            }
        });

        final Button move = (Button)findViewById(R.id.move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation moveAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.move);
                view.startAnimation(moveAnim);
            }
        });

        final Button scale = (Button)findViewById(R.id.scale);
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation scaleAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
                view.startAnimation(scaleAnim);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
