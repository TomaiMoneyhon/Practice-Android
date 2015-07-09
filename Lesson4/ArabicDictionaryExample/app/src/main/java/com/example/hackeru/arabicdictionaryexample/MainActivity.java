package com.example.hackeru.arabicdictionaryexample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Hashtable;

public class MainActivity extends Activity {

    private Hashtable<String,String> milon = new Hashtable<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        milon.put("hello","marhaba");
        milon.put("cat","biss");
        milon.put("book","ktab");
        milon.put("thanks","shukran");
        TextView textView =(TextView)findViewById(R.id.arabic_result);
        String source = getIntent().getStringExtra("wordToTranslate");
        String result = milon.get(source);
        if(result==null)
            textView.setText("no translation in arabic");
        else textView.setText(result);
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
