package com.example.hackeru.fileexamplethird;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    SharedPreferences sp;
    int counter;
    Bitmap bitmap;

    private static final int TAKE_PICTURE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("counterFile", Activity.MODE_PRIVATE);
        counter = sp.getInt("counter",0);
        iv = (ImageView)findViewById(R.id.iv);
        bitmap = HandleBitmap.readFileFromInternalStorage(this,"pic"+counter+".jpeg");
        if(bitmap!=null) {
            iv.setImageBitmap(bitmap);
        }

        Button takePic = (Button)findViewById(R.id.take_picture);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,TAKE_PICTURE);
            }
        });



        Button save  = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = sp.getInt("counter",0);
                counter++;
                HandleBitmap.writeBitmapToInternalStorage(MainActivity.this,bitmap,"pic"+counter+".jpeg");
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("counter",counter);
                editor.commit();
                Toast.makeText(MainActivity.this,"pic"+counter+".jpeg",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if(requestCode == TAKE_PICTURE) {
                bitmap = (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(bitmap);
            }
        }
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
