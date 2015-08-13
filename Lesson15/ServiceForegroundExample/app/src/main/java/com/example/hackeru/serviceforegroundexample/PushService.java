package com.example.hackeru.serviceforegroundexample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Hackeru on 13/08/2015.
 */
public class PushService extends Service {

    final int NOTIF_ID = 1;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIF_ID,getNotification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Notification getNotification() {

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        int icon = android.R.drawable.star_on;
        String title = "Service is running";
        String message = "Press the notification for....";

        Notification.Builder builder = new Notification.Builder(this);
        Notification notification = builder.setContentIntent(pendingIntent).setSmallIcon(icon).setContentText(message).setContentTitle(title).build();

        return notification;
    }
}
