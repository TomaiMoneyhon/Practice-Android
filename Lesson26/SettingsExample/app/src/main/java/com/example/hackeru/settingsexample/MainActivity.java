package com.example.hackeru.settingsexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    CheckBox prefCheckBox;
    TextView prefsText1;
    TextView prefsText2;
    ToggleButton toggleBtnPrefs;

    final static int SETTINGS_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefCheckBox = (CheckBox)findViewById(R.id.checkbox_prefs);
        prefsText1 = (TextView)findViewById(R.id.textview_prefs_1);
        prefsText2 = (TextView)findViewById(R.id.textview_prefs_2);
        toggleBtnPrefs = (ToggleButton)findViewById(R.id.toggle_btn_prefs);


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

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,PrefActivity.class);
            startActivityForResult(intent,SETTINGS_REQUEST);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SETTINGS_REQUEST) {

            SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
            boolean checkBoxValue = shared.getBoolean("checkbox_prefs",false);
            boolean toggleValue = shared.getBoolean("switch_prefs",false);
            String editTextValue = shared.getString("edittext_prefs","");
            String listValue = shared.getString("list_prefs","");

            prefCheckBox.setChecked(checkBoxValue);
            prefsText1.setText(editTextValue);
            prefsText2.setText(listValue);
            toggleBtnPrefs.setChecked(toggleValue);
        }
    }
}
