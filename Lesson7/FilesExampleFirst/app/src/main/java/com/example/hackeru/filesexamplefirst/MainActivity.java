package com.example.hackeru.filesexamplefirst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText)findViewById(R.id.text_to_save);
        final TextView textView = (TextView)findViewById(R.id.text_loaded);
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOutputStream=null;
                try {
                    fileOutputStream = openFileOutput("details.txt", MODE_APPEND);
                    String str = editText.getText().toString();
                    fileOutputStream.write(str.getBytes(),0,str.getBytes().length);
                }catch (FileNotFoundException ex) {
                    Log.d("outputstream","File not found");
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button load = (Button)findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileInputStream= null;
                try {
                    fileInputStream = openFileInput("details.txt");
                    byte[] buffer = new byte[1024];
                    int read=0;
                    StringBuffer sb  = new StringBuffer();
                    while ((read = fileInputStream.read(buffer)) != -1) {
                        String str = new String(buffer);
                        sb.append(str);
                        Log.i("bytes read",""+read);
                    }
                    textView.setText(sb.toString());
                }catch (FileNotFoundException ex) {
                    Log.d("inputstream","File not found");
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    fileInputStream.close();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
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
