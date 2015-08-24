package com.example.hackeru.locationexamplefirst;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener{

    TextView latTv,lonTv;
    LocationManager locMan;
    Geocoder gCoder;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latTv = (TextView)findViewById(R.id.latTv);
        lonTv = (TextView)findViewById(R.id.lonTv);

        locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        gCoder = new Geocoder(this);

        Button startLoc = (Button)findViewById(R.id.start_loc);
        startLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,MainActivity.this);
                }
                else locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1,1,MainActivity.this);
            }
        });

        Button stopLoc = (Button)findViewById(R.id.stop_loc);
        stopLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locMan.removeUpdates(MainActivity.this);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

        final double lat = location.getLatitude(),lon = location.getLongitude();
        latTv.setText("Latitude: " + lat);
        lonTv.setText("Longitude:  " + lon);
        new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    List<Address> info = gCoder.getFromLocation(lat, lon, 1);
                    if(info.size()!=0) {
                        final Address theAddress = info.get(0);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView infoTv = (TextView)findViewById(R.id.loc_address);
                                infoTv.setText(theAddress.getCountryName() + "," + theAddress.getLocality());
                            }
                        });
                    }
                }catch (IOException ex) {
                    Log.d("gecoder","no internet connection");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"No internet conncetion",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }.start();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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
