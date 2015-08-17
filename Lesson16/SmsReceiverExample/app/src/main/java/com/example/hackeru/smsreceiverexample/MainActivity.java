package com.example.hackeru.smsreceiverexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    BroadcastReceiver updateUiSmsReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView messageSenderTv = (TextView)findViewById(R.id.message_sender);
        final TextView messageBodyTv = (TextView)findViewById(R.id.mesage_text);

        if(getIntent().hasExtra("message") && getIntent().hasExtra("number")) {
            messageBodyTv.setText(getIntent().getStringExtra("message"));
            messageSenderTv.setText(getIntent().getStringExtra("number"));
        }

        //Update UI if the activity already running
        //create and register receiver programaticly
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.pzlapps.kill");
        updateUiSmsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };
        registerReceiver(updateUiSmsReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(updateUiSmsReceiver);
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
