package com.example.hackeru.listactivityexample;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* ArrayList<String> myList = new ArrayList<>();
        myList.add("one");
        myList.add("two");
        myList.add("five");*/

        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Rexi",2));
        dogs.add(new Dog("Humi",14));
        dogs.add(new Dog("Elvis",7));
        //ArrayAdapter<Dog> adapter = new ArrayAdapter<Dog>(this,android.R.layout.simple_list_item_1,dogs);
        ArrayAdapter<Dog> adapter = new ArrayAdapter<Dog>(this,R.layout.list_row,R.id.line_text,dogs);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        LinearLayout linearLayout = (LinearLayout)v;
        TextView textView = (TextView)linearLayout.findViewById(R.id.line_text);
        //TextView textView = (TextView)v;
        Toast.makeText(MainActivity.this,textView.getText().toString(),Toast.LENGTH_SHORT).show();
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
