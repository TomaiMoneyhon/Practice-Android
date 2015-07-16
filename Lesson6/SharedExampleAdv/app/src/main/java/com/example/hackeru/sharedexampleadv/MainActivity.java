package com.example.hackeru.sharedexampleadv;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    public static final String MY_SHARED = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(MY_SHARED,Activity.MODE_PRIVATE);//shared for all activities!

        String firstName = sp.getString("fname",null);
        String lastName = sp.getString("lname",null);
        if(firstName!=null && lastName!=null) {
            ((EditText)findViewById(R.id.first_name_input)).setText(firstName);
            ((EditText)findViewById(R.id.last_name_iunput)).setText(lastName);
        }

        Button second = (Button)findViewById(R.id.second_activity_btn);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        String fnameStr = ((EditText)findViewById(R.id.first_name_input)).getText().toString();
        String lnameStr = ((EditText)findViewById(R.id.last_name_iunput)).getText().toString();
        editor.putString("fname",fnameStr);
        editor.putString("lname",lnameStr);
        editor.commit();
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
