package com.example.hackeru.activityresultintentexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TEST_ACTIVITY = 0;
    public static final int CAMERA_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startTest = (Button)findViewById(R.id.test_btn);
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Test.class);
                String name = ((TextView)findViewById(R.id.student_name)).getText().toString();
                intent.putExtra("name",name);
                startActivityForResult(intent,TEST_ACTIVITY);

               /* new Thread() { //if a time out for the second activity is needed
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                            finishActivity(TEST_ACTIVITY);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();*/
            }
        });

        Button cameraButton = (Button)findViewById(R.id.camera_btn);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT,path-uri); //this is for getting the fullsize image at the uri passed
                startActivityForResult(intent, CAMERA_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TEST_ACTIVITY) {
            if(resultCode==RESULT_OK) {
                int grade = data.getIntExtra("grade", 0);
                TextView textView = (TextView)findViewById(R.id.test_result);
                textView.setText("Your grade is: " + grade);
            }
            else  {
                Toast.makeText(this,"Quitter!!!",Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode==CAMERA_ACTIVITY) {
            if(resultCode==RESULT_OK) {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                imageView.setLayoutParams(params);
                LinearLayout ll = (LinearLayout)findViewById(R.id.main_layout);
                ll.addView(imageView);
            }
            else {
                Toast.makeText(this,"Abort camera",Toast.LENGTH_SHORT).show();
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
