package com.example.hackeru.picturesgridview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.pic_grid);
        Integer[] flags = {R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_greece,R.drawable.flag_israel,
                            R.drawable.flag_romania,R.drawable.flag_italy,R.drawable.flag_russia,R.drawable.flag_thailand,R.drawable.flag_usa,
                R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_greece,R.drawable.flag_israel,
                R.drawable.flag_romania,R.drawable.flag_italy,R.drawable.flag_russia,R.drawable.flag_thailand,R.drawable.flag_usa,
                R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_greece,R.drawable.flag_israel,
                R.drawable.flag_romania,R.drawable.flag_italy,R.drawable.flag_russia,R.drawable.flag_thailand,R.drawable.flag_usa,
                R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_greece,R.drawable.flag_israel,
                R.drawable.flag_romania,R.drawable.flag_italy,R.drawable.flag_russia,R.drawable.flag_thailand,R.drawable.flag_usa,
                R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_greece,R.drawable.flag_israel,
                R.drawable.flag_romania,R.drawable.flag_italy,R.drawable.flag_russia,R.drawable.flag_thailand,R.drawable.flag_usa};
        ImageAdapter imageAdapter = new ImageAdapter(this,flags);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,ShowImageActivity.class);
                intent.putExtra("image_id",(Integer)view.getTag());
                startActivity(intent);
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
