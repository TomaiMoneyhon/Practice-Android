package com.example.hackeru.pointuppointdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Here the View>onClickListener is implemented with anonynymus inner class
public class MainActivity extends Activity{

    TextView textView;
    Button upButton;
    Button downButton;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.score_text);
        upButton = (Button)findViewById(R.id.btn_up);
        downButton = (Button)findViewById(R.id.btn_down);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                textView.setText("You now have " + score);
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score--;
                textView.setText("You now have " + score);
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

/* Special Inner class named MyPointsButtonListener implements the View.OnClickListenerInterface
public class MainActivity extends Activity{


    TextView textView;
    Button upButton;
    Button downButton;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.score_text);
        upButton = (Button)findViewById(R.id.btn_up);
        downButton = (Button)findViewById(R.id.btn_down);

        upButton.setOnClickListener(new MyPointsButtonListener());
        downButton.setOnClickListener(new MyPointsButtonListener());
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

    class MyPointsButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(view.getId()==upButton.getId()) score++;
            else if(view.getId()==downButton.getId()) score--;
            textView.setText("You now have " + score + " points");
        }

    }
}*/



/* The MainActivity imlplements the View.OnClickLictener interface
public class MainActivity extends Activity implements View.OnClickListener{


    TextView textView;
    Button upButton;
    Button downButton;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.score_text);
        upButton = (Button)findViewById(R.id.btn_up);
        downButton = (Button)findViewById(R.id.btn_down);

        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==upButton.getId()) score++;
        else if(view.getId()==downButton.getId()) score--;
        textView.setText("You now have " + score + " points");
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
*/