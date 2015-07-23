package com.example.hackeru.progressdialogexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(); //new Handler object created onj the main thread!!!!

    boolean abort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button)findViewById(R.id.start_process);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dlg = new ProgressDialog(MainActivity.this);
                dlg.setTitle("Please wait");
                dlg.setMessage("Hit cancel to abort...");
                dlg.setCancelable(false);
                dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        abort = true;
                    }
                });
                dlg.setProgress(0);
                dlg.setMax(100);
                new Thread()
                {
                    @Override
                    public void run() {
                        while (!abort) {
                            try {
                                Thread.sleep(100);
                            }catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            handler.post(new Runnable() { //UI changes must be made from the main thread
                                @Override
                                public void run() {
                                    dlg.incrementProgressBy(1);
                                }
                            });
                            if(dlg.getProgress()==dlg.getMax())
                            {
                                finish();
                            }
                        }
                        if(abort) {
                            dlg.cancel();
                            abort=false;
                        }

                    }
                }.start();
                dlg.show();

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
