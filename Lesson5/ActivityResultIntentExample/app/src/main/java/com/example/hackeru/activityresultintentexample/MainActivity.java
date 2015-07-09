package com.example.hackeru.activityresultintentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TEST_ACTIVITY = 0;

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

                /*new Thread() { //if a time out for the second activity is needed
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
