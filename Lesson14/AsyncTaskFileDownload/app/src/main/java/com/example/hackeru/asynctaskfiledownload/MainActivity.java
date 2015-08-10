package com.example.hackeru.asynctaskfiledownload;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    final String picPath = "https://upload.wikimedia.org/wikipedia/commons/8/82/Steps_in_Windy_Nook_Nature_Reserve.JPG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image_downloaded);
        Button startDownload = (Button)findViewById(R.id.download_file);
        startDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(null);
                new DownloadFileFromUrl().execute(picPath);
            }
        });
    }

    class DownloadFileFromUrl extends AsyncTask<String,Integer,String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Downloading file, please wait...");
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String outputFilePath = null;
            try {
                URL url  = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                int fileLength = connection.getContentLength(); //returns -1 if the size cannot be represnted in an int

                InputStream inputStream = new BufferedInputStream(url.openStream(),1024);
                // /sdcard/
                outputFilePath = Environment.getExternalStorageDirectory().toString() + "/downloadfile.jpg";
                Log.d("AsyncTaskExample",outputFilePath);
                OutputStream outputStream = new FileOutputStream(outputFilePath);

                byte data[] = new byte[1024];

                long total=0;
                int count;
                while ((count = inputStream.read(data))!= -1) {
                    total+=count;
                    publishProgress((int)(total*100)/fileLength); //ui update on main thread through onProgressUpdate
                    outputStream.write(data,0,count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }catch (MalformedURLException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            return outputFilePath;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pDialog.setProgress(values[0]);
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            imageView.setImageDrawable(Drawable.createFromPath(s));
        }
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
