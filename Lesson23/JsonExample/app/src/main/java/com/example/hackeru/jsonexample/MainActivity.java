package com.example.hackeru.jsonexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public String readFeed(String urlStr) {
        StringBuilder sb = new StringBuilder();
        try {

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            int statusCode  = connection.getResponseCode();
            if(statusCode==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine())!=null) {
                    sb.append(line + "\n");
                }
                reader.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }

    private class ReadWeatherJSONFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return readFeed(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
               /*
               //---sample result---
            {
                "weatherObservation": {
                     "clouds":"scattered clouds",
                     "weatherCondition":"n/a",
                     "observation":"KCFV 090852Z AUTO 06005KT 10SM SCT090 SCT110 24/20 A3000 RMK AO2 SLP148 T02390200 53002",
                     "windDirection":60,
                     "ICAO":"KCFV",
                     "seaLevelPressure":1014.8,
                     "elevation":225,
                     "countryCode":"US",
                     "lng":-95.56666666666666,
                     "temperature":"23.9",
                     "dewPoint":"20",
                     "windSpeed":"05",
                     "humidity":78,
                     "stationName":"Coffeyville, Coffeyville Municipal Airport",
                     "datetime":"2012-07-09 08:52:00",
                     "lat":37.083333333333336
                }
            }
                */
            TextView textView = (TextView)findViewById(R.id.json_result);
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.setText(s);
            Log.d("Json result",s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject weatherItems = jsonObject.getJSONObject("weatherObservation");
                Toast.makeText(MainActivity.this,weatherItems.getString("stationName")+"-"+weatherItems.getString("countryCode")
                            +"-Temp:"+weatherItems.getInt("temperature") + "-Clouds:" + weatherItems.getString("clouds"),Toast.LENGTH_LONG).show();


            }catch (JSONException ex) {
                Log.d("Json","json error" + ex.getLocalizedMessage());
            }
        }
    }

    private class ReadJSONPlacesFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return readFeed(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //---sample result---
            /*{
                "postalCodes": [
                {
                    "adminCode3":"3203",
                        "adminName2":"Wahlkreis St. Gallen",
                        "adminName3":"St. Gallen",
                        "adminCode2":"1721",
                        "adminCode1":"SG",
                        "postalCode":"9011",
                        "countryCode":"CH",
                        "lng":9.399858534040646,
                        "placeName":"St. Gallen",
                        "lat":47.414775328611945,
                        "adminName1":"Kanton St. Gallen"
                },
                {
                    "adminCode1":"GS",
                        "postalCode":"9011",
                        "countryCode":"HU",
                        "lng":17.781944437499998,
                        "placeName":"Gyor",
                        "lat":47.607638900000005,
                        "adminName1":"Gyor-Moson-Sopron"
                },
                {
                    "adminName2":"Troms�",
                        "adminCode2":"1902",
                        "adminCode1":"19",
                        "postalCode":"9011",
                        "countryCode":"NO",
                        "lng":18.95508,
                        "placeName":"Troms�",
                        "lat":69.6489,
                        "adminName1":"Troms"
                },
                {
                    ...
                    ...
                }
                ]
            }
            */
            TextView textView = (TextView)findViewById(R.id.json_result);
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.setText(s);
            Log.d("Json result",s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray postalItems = jsonObject.getJSONArray("postalCodes");

                for(int i=0;i<postalItems.length();i++) {
                    JSONObject postalItem = postalItems.getJSONObject(i);
                    Toast.makeText(MainActivity.this,postalItem.getString("postalCode") + " - " +
                                    postalItem.getString("placeName") + " - " + postalItem.getString("countryCode"),Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button weatherBtn  = (Button)findViewById(R.id.weather_btn);
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText latEt = (EditText)findViewById(R.id.latitude);
                EditText lngEt = (EditText)findViewById(R.id.longitude);

                new ReadWeatherJSONFeedTask().execute("http://ws.geonames.org/findNearByWeatherJSON?" +
                        "lat=" + latEt.getText().toString()+ "&lng=" + lngEt.getText().toString()+"&username=erankatsav");
            }
        });

        Button placesBtn = (Button)findViewById(R.id.places_btn);
        placesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText postalCodeEt = (EditText)findViewById(R.id.postal_code);

                new ReadJSONPlacesFeedTask().execute("http://api.geonames.org/postalCodeSearchJSON?postalcode=" +
                        postalCodeEt.getText().toString()+"&maxRows=10&username=erankatsav");
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
