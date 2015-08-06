package com.example.hackeru.sampleproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    ImageView iv;
    final int CAMERA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.image);
        Button takePic = (Button)findViewById(R.id.tak_pic);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA);
            }
        });

        Button saveBtn = (Button)findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.name);
                EditText editText1 = (EditText)findViewById(R.id.name_second);
                String name  = editText.getText().toString();
                String name1 = editText1.getText().toString();
                Person person = new Person(name,bitmap);
                Person person1 = new Person(name1,bitmap);
                ArrayList<Person> list = new ArrayList<Person>();
                list.add(person);
                list.add(person1);

                FileOutputStream fos = null;
                ObjectOutputStream oos = null;
                try {
                    fos = openFileOutput("person", Activity.MODE_PRIVATE);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(list);
                }catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    try{
                        if(oos!=null) oos.close();
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


                editText.setText("");
                editText1.setText("");
                iv.setImageBitmap(null);

            }
        });

        Button loadBtn = (Button)findViewById(R.id.load);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Person> list = null;
                EditText editText = (EditText) findViewById(R.id.name);
                EditText editText1 = (EditText)findViewById(R.id.name_second);
                FileInputStream fis = null;
                ObjectInputStream ois = null;
                try {
                    fis = openFileInput("person");
                    ois = new ObjectInputStream(fis);
                    list = (ArrayList<Person>) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (ois != null) ois.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if(list!=null) {
                    editText.setText(list.get(0).getName());
                    editText1.setText(list.get(1).getName());
                    iv.setImageBitmap(list.get(0).getPicture());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA && resultCode==RESULT_OK) {
            bitmap = (Bitmap)data.getExtras().get("data");
            iv.setImageBitmap(bitmap);
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
