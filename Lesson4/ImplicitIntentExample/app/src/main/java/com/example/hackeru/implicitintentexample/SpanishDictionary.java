package com.example.hackeru.implicitintentexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by hackeru on 06/07/2015.
 */
public class SpanishDictionary extends Activity {

    private Hashtable<String,String> milon = new Hashtable<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        milon.put("hello","hola");
        milon.put("thanks","grascias");
        milon.put("cat","gato");
        milon.put("book","libro");
        setContentView(R.layout.seond_activity);
        TextView textView = (TextView)findViewById(R.id.spainish_result);
        String wordToTranslate = getIntent().getStringExtra("wordToTranslate");
        String result = milon.get(wordToTranslate);
        if(result==null)
            textView.setText("No translation");
        else textView.setText(result);
    }
}
