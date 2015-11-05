package com.example.hackeru.httpconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = (TextView)findViewById(R.id.result);

        Button getBtn = (Button)findViewById(R.id.get_btn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTv.setText(null);
                new DownloadTextTask()
                        .execute("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=ILS&ToCurrency=USD","get");
            }
        });

        Button postBtn = (Button)findViewById(R.id.post_btn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultTv.setText(null);
                new DownloadTextTask().execute("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate","post");
            }
        });

        Button picBtn = (Button)findViewById(R.id.image_get_btn);
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadImageTask().execute("http://www.mayoff.com/5-01cablecarDCP01934.jpg");

            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView)findViewById(R.id.image);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
           return downloadImage(strings[0]);
        }
    }
    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            inputStream = openHttpGetConnection(url);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private class DownloadTextTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            if(strings[1].equals("get")) return downloadText(strings[0],true);
            else return  downloadText(strings[0],false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resultTv.setText(s);
        }
    }

    private String downloadText(String url,boolean isGet) { //converts the inputstream into string
        int BUFFER_SIZE = 1024;
        InputStream in = null;
        try {
            if(isGet) in = openHttpGetConnection(url);
            else in = openHttpPostConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charsRead; // amount of chars read pr -1 if EOF
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charsRead = isr.read(inputBuffer)) != -1) {
                String readString  = String.copyValueOf(inputBuffer,0,charsRead);
                str+=readString;
            }
            isr.close();
        }catch (Exception e) {

        }
        return str;
    }

    private InputStream openHttpPostConnection(String urlStr) {
        InputStream inputStream = null;
        try {

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("FromCurrency", "EUR")
                    .appendQueryParameter("ToCurrency", "USD");
            String query = builder.build().getEncodedQuery();

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

           /* HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            //set the header
            httpPost.setHeader("Host","www.webservicex.net");
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");

            //add the key values pairs to the post request
            List<NameValuePair> nameValuePairs = new ArrayList<>(2);

            nameValuePairs.add(new BasicNameValuePair("FromCurrency","EUR"));
            nameValuePairs.add(new BasicNameValuePair("ToCurrency","USD"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();*/

        }catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private InputStream openHttpGetConnection(String urlStr) {//open a get Connection and return the inputstream
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            /*HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url)); //can't be executed on main thread
            inputStream = httpResponse.getEntity().getContent();*/
        }catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
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
