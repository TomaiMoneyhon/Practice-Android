package com.example.hackeru.customadapterexample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)findViewById(R.id.taxpayers_list);
        TaxPayer[] taxPayers = {new TaxPayer("Dan",100.0,10.0,10.0,true),new TaxPayer("Ran",1000.4,200.2,500.2,false),
        new TaxPayer("Yael",100.0,10.0,10.0,false),new TaxPayer("Adi",1000.4,200.2,500.2,false),
                new TaxPayer("Moshon",100.0,10.0,10.0,false),new TaxPayer("Yehezkel",1000.4,200.2,500.2,false),
                new TaxPayer("Omer",200.0,700.0,100.0,false),new TaxPayer("Amos",1000.4,200.2,500.2,false),
                new TaxPayer("El-Zariz",900.0,0.0,1000.0,false)};
        TaxPayerAdapter tpAdapter = new TaxPayerAdapter(taxPayers,this);
        lv.setAdapter(tpAdapter);
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
