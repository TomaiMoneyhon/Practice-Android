package com.example.hackeru.musicplayingservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Hackeru on 13/08/2015.
 */
public class PlayService extends Service implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener{

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.reset();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        stopSelf();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String link = intent.getStringExtra("link");
        mediaPlayer.reset();
        if(!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.setDataSource(link);
                //prepare is sync and will block the main thread while prepareAsync is done on background thread and returns to the listener when ready
                mediaPlayer.prepareAsync();
            }catch (IOException ex) {
                Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        startForeground(1,getNotification());
        return START_REDELIVER_INTENT;
    }

    private Notification getNotification() {

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("key","isPlaying");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        int icon = android.R.drawable.star_on;
        String title = "Music player";
        String message = "Music is playing now";

        Notification.Builder builder = new Notification.Builder(this);
        Notification notification = builder.setContentText(message).setContentTitle(title).setSmallIcon(icon).setContentIntent(pendingIntent).build();

        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        return notification;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null) {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
