package com.example.hackeru.contactmanageapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hackeru on 03/08/2015.
 */
public class AddContactActivity extends ActionBarActivity {

    final int CAMERA = 0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        iv = (ImageView)findViewById(R.id.contact_image);
        Button takePicBtn  = (Button)findViewById(R.id.take_picture);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA);
            }
        });

        Button birthdayBtn = (Button)findViewById(R.id.birth_date_btn);
        birthdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(AddContactActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        TextView textView = (TextView)findViewById(R.id.birth_date_output);
                        textView.setText("Birthday chosen: " + i + "/"+(i1+1)+"/"+i2);
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        Button timeBtn = (Button)findViewById(R.id.call_time_btn);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(AddContactActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        TextView textView = (TextView)findViewById(R.id.call_time_output);
                        textView.setText("Call time chosen: " + i + ":" + i1);
                    }
                },hour,minute,true);
                tpd.show();
            }
        });

        Button daysBtn = (Button)findViewById(R.id.best_days_btn);
        daysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> days = new ArrayList<String>();
                final String[] daysStr = getResources().getStringArray(R.array.days);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity.this);
                builder.setTitle("Pick best days to call");
                builder.setMultiChoiceItems(daysStr, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b) days.add(daysStr[i]);
                        else days.remove(daysStr[i]);
                    }
                });
                builder.setPositiveButton("save",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView textView = (TextView)findViewById(R.id.best_days_output);
                        textView.setText("Best days: " + days.toString());
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA) {
            if(resultCode==RESULT_OK) {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                iv.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.back:
                finish();
                return true;
            case R.id.save_contact:

                new AlertDialog.Builder(this).setTitle("Please confirm").setMessage("Are you sure you want to save?").setCancelable(false).
                        setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Yes, I am sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
