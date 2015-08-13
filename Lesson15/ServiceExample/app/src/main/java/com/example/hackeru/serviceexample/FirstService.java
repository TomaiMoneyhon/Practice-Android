package com.example.hackeru.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Hackeru on 13/08/2015.
 */
public class FirstService extends Service {

    Handler handler = new Handler();
    FirstThread firstThread=null;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"on create",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"on start command",Toast.LENGTH_SHORT).show();

        if(firstThread==null) {
            firstThread = new FirstThread();
            firstThread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"on destroy",Toast.LENGTH_SHORT).show();

        if(firstThread!=null) {
            firstThread.abort=true;
            firstThread=null;
        }
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

    class FirstThread extends Thread {
        int counter=30;
        boolean abort=false;
        @Override
        public void run() {
            super.run();

            while (counter>0 && !abort) {
                try {
                    Thread.sleep(1000);
                    Log.d("service",counter+"");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FirstService.this,counter+"",Toast.LENGTH_SHORT).show();

                        }
                    });
                    counter--;
                }catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
