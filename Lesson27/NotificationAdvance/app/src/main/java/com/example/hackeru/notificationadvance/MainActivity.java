package com.example.hackeru.notificationadvance;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int PROG_NOTIF_ID = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String value;
        if((value = getIntent().getStringExtra("key"))!=null) {
            if(value.equals("prev"))
                Toast.makeText(MainActivity.this, "Previous song", Toast.LENGTH_SHORT).show();
            else if(value.equals("play"))
                Toast.makeText(MainActivity.this, "play music", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "Next song", Toast.LENGTH_SHORT).show();

        }
        Button btn = (Button)findViewById(R.id.notif_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Notification.Builder builder = new Notification.Builder(MainActivity.this);


                builder.setContentTitle("Best music player").setContentText("now playing moshik afia, my precious")
                        .setSmallIcon(R.mipmap.ic_launcher);

                Random random = new Random();
                Intent prevIntent = new Intent(MainActivity.this,MainActivity.class).putExtra("key", "prev");
                PendingIntent prevPendIntent = PendingIntent.getActivity(MainActivity.this, random.nextInt(), prevIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.addAction(android.R.drawable.ic_media_previous,"prev",prevPendIntent);

                Intent playIntent = new Intent(MainActivity.this,MainActivity.class).putExtra("key", "play");
                PendingIntent playPendIntent = PendingIntent.getActivity(MainActivity.this,  random.nextInt(), playIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.addAction(android.R.drawable.ic_media_play,"play",playPendIntent);

                Intent nextIntent = new Intent(MainActivity.this,MainActivity.class).putExtra("key", "next");
                PendingIntent nextPendIntent = PendingIntent.getActivity(MainActivity.this,  random.nextInt(), nextIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.addAction(android.R.drawable.ic_media_next,"next",nextPendIntent);

                Notification notification = builder.build();

                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(111,notification);
            }
        });

        Button notif  =(Button)findViewById(R.id.prog_notid_btn);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                final Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setContentTitle("Picture download").setContentText("Download in progress please wait").setSmallIcon(android.R.drawable.ic_menu_camera);

                new Thread() {

                    @Override
                    public void run() {
                        super.run();
                        for (int i=0;i<100;i+=5) {
                            builder.setProgress(100,i,false);
                            notificationManager.notify(PROG_NOTIF_ID,builder.build());

                            try {
                                Thread.sleep(500);
                            }catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        builder.setContentText("Download complete").setProgress(0,0,false);
                        notificationManager.notify(PROG_NOTIF_ID,builder.build());
                    }
                }.start();
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
