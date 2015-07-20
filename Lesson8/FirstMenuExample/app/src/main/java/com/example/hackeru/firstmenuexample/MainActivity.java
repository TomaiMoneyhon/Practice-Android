package com.example.hackeru.firstmenuexample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        switch (id) {
            case R.id.action_save:
                SharedPreferences sp  = getSharedPreferences("details", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                EditText firstNameET = (EditText)findViewById(R.id.first_name_input);
                EditText lastNameET = (EditText)findViewById(R.id.last_name_input);
                editor.putString("fname",firstNameET.getText().toString());
                editor.putString("lname", lastNameET.getText().toString());
                editor.commit();
                break;
            case R.id.action_register:
                Intent intent = new Intent(this,RegisterForm.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
