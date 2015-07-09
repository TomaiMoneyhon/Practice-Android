package com.example.hackeru.avanceguiexample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    ImageView imageView;

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        imageView.getDrawable().setAlpha(seekBar.getProgress());

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this,"seek bar change started",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this,"seek bar change finished",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.main_image);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.image_tranparacy);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(255);
        seekBar.setProgress(255);


        Switch mySwitch = (Switch)findViewById(R.id.image_switch);
        mySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("visibility",imageView.getVisibility()+"");
                imageView.setVisibility(imageView.getVisibility()==View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
                seekBar.setVisibility(seekBar.getVisibility()==View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
            }
        });

        final EditText editText = (EditText)findViewById(R.id.editText1);
        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggleB);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton tb = (ToggleButton)view;
                if(tb.isChecked()) {
                    editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
                }
                else {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScroll);
        ImageButton imageButton = (ImageButton)findViewById(R.id.image_button);
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                horizontalScrollView.setVisibility(View.GONE);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearL);
                for(int i=0;i<10;i++) {
                    Button btn  = new Button(MainActivity.this);
                    btn.setText(i+"");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60,60);
                    btn.setLayoutParams(layoutParams);
                    btn.setTag("i="+i);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Button b = (Button)view;
                            Toast.makeText(MainActivity.this,b.getTag().toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                    linearLayout.addView(btn);

                }
                return false;
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
