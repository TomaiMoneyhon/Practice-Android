package com.example.hackeru.threadexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Bitmap bitmap=null;
    ImageView imageView;
    final String PICTURE = "http://www.fujifilmusa.com/products/digital_cameras/x/fujifilm_x_s1/sample_images/img/index/ff_x_s1_005.JPG";
    Button showImageBtn;
    Handler handler = new Handler(); //must be created on main thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showImageBtn = (Button)findViewById(R.id.show_image);
        showImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(bitmap);
            }
        });
        tv = (TextView)findViewById(R.id.progress_text);
        Button btn  = (Button)findViewById(R.id.download);
        imageView = (ImageView)findViewById(R.id.image_loaded);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicture();
            }
        });
    }

    private void getPicture() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Started download...");
                    }
                });
                try {
                    URL url = new URL(PICTURE);
                    HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
                    if(httpCon.getResponseCode()>=400) return;
                    InputStream inputStream = httpCon.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);

                }catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("download finished!");
                        showImageBtn.setVisibility(View.VISIBLE);
                    }
                });

            }
        }.start();

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
