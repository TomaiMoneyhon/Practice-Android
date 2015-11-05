package com.example.hackeru.smsexample;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String SEND_PI = "SMS_SENT";
    BroadcastReceiver sendBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button)findViewById(R.id.send_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numberEt  = (EditText)findViewById(R.id.number);
                String number = numberEt.getText().toString();

                EditText messageEt  = (EditText)findViewById(R.id.message);
                String message = messageEt.getText().toString();

                sendSms(number,message);

                messageEt.setText(null);
                numberEt.setText(null);
            }
        });


        sendBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(MainActivity.this,"Message sent!!",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                    default:
                        Toast.makeText(MainActivity.this, "Message failure!!", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    private void sendSms(String number, String message) {

        PendingIntent sendPI = PendingIntent.getBroadcast(this,0,new Intent(SEND_PI),0);

        SmsManager smsManager = SmsManager.getDefault();
        if(number!=null) number= number.replaceAll("[^\\d.]","");
        smsManager.sendTextMessage(number,null,message,sendPI,null);



    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(sendBroadcastReceiver,new IntentFilter(SEND_PI));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(sendBroadcastReceiver);
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
