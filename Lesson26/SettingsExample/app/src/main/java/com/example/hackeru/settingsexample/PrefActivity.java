package com.example.hackeru.settingsexample;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by hackeru on 26/10/2015.
 */
public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.HONEYCOMB) {
            addPreferencesFromResource(R.xml.preferences);
        } else {
            getFragmentManager().beginTransaction().replace(android.R.id.content,new PrefsFragment()).commit();
        }
    }
}
