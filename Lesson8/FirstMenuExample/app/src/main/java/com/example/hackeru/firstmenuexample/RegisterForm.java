package com.example.hackeru.firstmenuexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by hackeru on 20/07/2015.
 */
public class RegisterForm extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        TextView textView = (TextView)findViewById(R.id.user_name_to_register);

        SharedPreferences sp = getSharedPreferences("details", Activity.MODE_PRIVATE);
        textView.setText("Welcome " + sp.getString("fname","") + " " + sp.getString("lname","") + " please register");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
