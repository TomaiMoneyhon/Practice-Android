package com.example.hackeru.screenonoffreceiver;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hackeru on 17/08/2015.
 */
public class ScreenRegisterService extends Service {

    BroadcastReceiver mReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver,filter);

        Notification.Builder builder = new Notification.Builder(this);
        Notification notification = builder.setSmallIcon(android.R.drawable.star_on).setContentText("this is service of screen change").
                setContentTitle("screen service").build();
        startForeground(1234,notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        stopForeground(true);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
