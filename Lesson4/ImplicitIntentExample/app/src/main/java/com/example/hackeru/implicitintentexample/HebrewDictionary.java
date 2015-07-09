package com.example.hackeru.implicitintentexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by hackeru on 06/07/2015.
 */
public class HebrewDictionary  extends Activity {

    private Hashtable<String,String> milon = new Hashtable<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        milon.put("hello", "shalom");
        milon.put("thanks","toda");
        milon.put("cat","hatul");
        milon.put("book","sefer");
        setContentView(R.layout.hebrew_layout);
        TextView textView = (TextView)findViewById(R.id.hebrew_result);
        String wordToTranslate = getIntent().getStringExtra("wordToTranslate");
        String result = milon.get(wordToTranslate);
        if(result==null)
            textView.setText("No translation");
        else textView.setText(result);

    }
}
