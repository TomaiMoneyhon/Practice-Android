package com.example.hackeru.inetentserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Hackeru on 13/08/2015.
 */
public class SecondService extends IntentService {

    Handler handler = new Handler();

    public SecondService() {
        super("secondservice");
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SecondService.this,"constructor",Toast.LENGTH_SHORT).show();
            }
        });
    }

    int counter;
    @Override
    protected void onHandleIntent(Intent intent) {

        counter = intent.getIntExtra("counter",20);
        while (counter>0) {
            try {
                Thread.sleep(1000);
                counter--;
                Log.d("intentservice",counter+"");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SecondService.this,counter+"",Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (InterruptedException ex) {

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"destrot service",Toast.LENGTH_SHORT).show();
    }
}
