package com.example.hackeru.externalapiexample;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wazeBtn = (Button)findViewById(R.id.waze_btn);
        wazeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText latEt = (EditText)findViewById(R.id.latitue);
                EditText lonEt = (EditText)findViewById(R.id.longitude);
                EditText addressEt = (EditText)findViewById(R.id.address);

                String urlStr=null;
                if(addressEt.getText()!=null && !addressEt.getText().toString().trim().equals("")){
                     urlStr  = "waze://?q=" + Uri.encode(addressEt.getText().toString()).toString()+"&navigate=yes";

                }
                else {
                     urlStr = "waze://?ll="+latEt.getText().toString()+","+lonEt.getText().toString()+"&navigate=yes";

                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlStr));
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException ex) {
                    //Go to market
                    //Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.waze"));
                    //startActivity(intent1);
                    //opne google maps with address if you want lat and long look below
                    Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q="+Uri.encode(addressEt.getText().toString()).toString()));
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    //use google.navigation:q=lat,lon
                }

            }
        });

        final Button whatsAppBtn = (Button)findViewById(R.id.whatsapp_btn);
        whatsAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText whatsappEt = (EditText)findViewById(R.id.message);
                String message = whatsappEt.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,message);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
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
