package com.example.hackeru.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final int NOTIF_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button push = (Button)findViewById(R.id.push);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //phase 1  - create the pending intent
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
               // intent.putExtra("key","Stam kishkush");
                EditText editText = (EditText)findViewById(R.id.input);
                intent.putExtra("key",editText.getText().toString());
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                //phase 2 - notificatiom details
                int icon = android.R.drawable.star_on;
                String notifTitle = "this is the title";
                String notifMesssage = "this is the notificatiom message";

                //phase 3  - create the notification using builder or the appcompat for under sdk 16
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                Notification notification = builder.setContentTitle(notifTitle).setContentText(notifMesssage).setContentIntent(pendingIntent).
                        setSmallIcon(icon).build();

                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;

                //phase 4 - get instance of the notification manager and post the notification
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(NOTIF_ID,notification);



            }
        });

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(NOTIF_ID);
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
