package com.example.hackeru.musicplayingservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean isPlaying;
    Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(this,PlayService.class);
        final Button musicBtn = (Button)findViewById(R.id.music_btn);
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying) {
                    isPlaying=false;
                    musicBtn.setText("play");
                    stopAudio();
                }
                else {
                    isPlaying=true;
                    musicBtn.setText("stop");
                    playAudio();
                }
            }
        });

        if(getIntent().hasExtra("key")) {//launced from notification
            isPlaying=true;
            musicBtn.setText("stop");
        }

    }

    private void playAudio() {

        String strLink = "http://www.pzlapps.com/bob_dylan.mp3";
        serviceIntent.putExtra("link",strLink);

        startService(serviceIntent);


    }

    private void stopAudio() {

        stopService(serviceIntent);
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
