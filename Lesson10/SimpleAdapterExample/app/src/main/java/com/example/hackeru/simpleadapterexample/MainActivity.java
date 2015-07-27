package com.example.hackeru.simpleadapterexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myList = (ListView)findViewById(R.id.state_list);

        ArrayList<HashMap<String,?>> data = new ArrayList<>();

        HashMap<String,Object> first = new HashMap<>();
        first.put("picture",R.drawable.flag_china);
        first.put("name","China");
        first.put("isGood",true);
        data.add(first);

        HashMap<String,Object> second = new HashMap<>();
        second.put("picture",R.drawable.flag_france);
        second.put("name","France");
        second.put("isGood",false);
        data.add(second);

        HashMap<String,Object> third = new HashMap<>();
        third.put("picture",R.drawable.flag_israel);
        third.put("name","Israel");
        third.put("isGood",true);
        data.add(third);

        HashMap<String,Object> forth = new HashMap<>();
        forth.put("picture",R.drawable.flag_greece);
        forth.put("name","Greece");
        forth.put("isGood",true);
        data.add(forth);

        HashMap<String,Object> fifth = new HashMap<>();
        fifth.put("picture",R.drawable.flag_russia);
        fifth.put("name","Russia");
        fifth.put("isGood",false);
        data.add(fifth);

        String[] keys = {"picture","name","isGood"};
        int[] ids = {R.id.line_image,R.id.line_text,R.id.line_checkbox};

        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.line_row,keys,ids) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null) {
                    convertView = getLayoutInflater().inflate(R.layout.line_row,null);
                }
                Button btn = (Button)convertView.findViewById(R.id.line_btn);
                final TextView textView = (TextView)convertView.findViewById(R.id.line_text);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Moving to " + textView.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                return super.getView(position, convertView, parent);
            }
        };
        myList.setAdapter(adapter);

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
