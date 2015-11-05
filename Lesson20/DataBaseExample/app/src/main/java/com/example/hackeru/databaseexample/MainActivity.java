package com.example.hackeru.databaseexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    static final String CREATE_TABLE_CMD="CREATE TABLE IF NOT EXISTS tbl_programmers(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, family TEXT);";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase database = openOrCreateDatabase("programmers.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        database.setVersion(1);
        database.execSQL(CREATE_TABLE_CMD);

        ListView listView = (ListView)findViewById(R.id.resultListView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);


        Button insertBtn = (Button)findViewById(R.id.insert);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEt = (EditText)findViewById(R.id.first);
                EditText lastEt = (EditText)findViewById(R.id.last);

                ContentValues values = new ContentValues();//Map object intended to add values to sql table
                values.put("name",nameEt.getText().toString());//first - column name, second column value
                values.put("family",lastEt.getText().toString());
                database.insert("tbl_programmers",null,values);
                nameEt.setText("");
                lastEt.setText("");
            }
        });

        Button showBtn = (Button)findViewById(R.id.show);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.clear();

                Cursor cursor = database.query("tbl_programmers",null,null,null,null,null,null); //selecting all records without nay special criteria or order

                int nameIndex = cursor.getColumnIndex("name");
                int lastIndex = cursor.getColumnIndex("family");

                while (cursor.moveToNext()) {
                    String programmer = cursor.getString(nameIndex) + " " + cursor.getString(lastIndex);
                    adapter.add(programmer);
                }
                adapter.notifyDataSetChanged();

                cursor.close();
            }
        });

        Button selectBtn = (Button)findViewById(R.id.search);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.clear();
                EditText nameEt = (EditText)findViewById(R.id.first);
                String where = "name LIKE '" + nameEt.getText().toString() + "%'";
                String[] cols = {"id","name"};
                Cursor cursor = database.query("tbl_programmers",cols,where,null,null,null,null);
                int nameIndex = cursor.getColumnIndex("name");
                while (cursor.moveToNext()) {
                    String programmer = cursor.getString(nameIndex);
                    adapter.add(programmer);
                }
                adapter.notifyDataSetChanged();
                cursor.close();
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
