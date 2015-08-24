package com.example.hackeru.locationexamplesecond;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locMan.getAllProviders();
        for(String provider:providers) {
            Toast.makeText(this,"Provider: " + provider + (locMan.isProviderEnabled(provider)?"Enabled":"Disabled"),Toast.LENGTH_SHORT).show();
            Log.i("Provider: ",provider);
            Log.i(provider,locMan.isProviderEnabled(provider)?"Enabled":"Disabled");
        }
        Criteria reqs = new Criteria();
        reqs.setAccuracy(Criteria.ACCURACY_LOW);//rough
        reqs.setBearingRequired(false);
        reqs.setPowerRequirement(Criteria.POWER_LOW);//batery power
        reqs.setAltitudeRequired(false);
        reqs.setSpeedRequired(false);
        reqs.setCostAllowed(false);
        String bestProvider = locMan.getBestProvider(reqs,false);
      // Log.i("Best provider: ",bestProvider);
        Toast.makeText(this,"Best provider: " + bestProvider,Toast.LENGTH_SHORT).show();
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
