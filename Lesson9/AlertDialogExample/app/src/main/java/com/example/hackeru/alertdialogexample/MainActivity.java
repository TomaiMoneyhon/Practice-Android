package com.example.hackeru.alertdialogexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int ALERT_DIALOG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.save_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(ALERT_DIALOG);
                MyListener myListener = new MyListener();
                new AlertDialog.Builder(MainActivity.this).setTitle("Save?").setMessage("Are you sure??").
                        setPositiveButton("Ok",myListener).setNegativeButton("No",myListener).
                        setNeutralButton("don't know", myListener).setCancelable(false).create().show();
            }
        });

        Button colorBtn = (Button)findViewById(R.id.select_color_btn);
        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] colors = getResources().getStringArray(R.array.colors);
                //getResources().getString(R.string.hello_world);
                new AlertDialog.Builder(MainActivity.this).setTitle("Pick a color").setItems(R.array.colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,colors[i],Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Don't care", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"You don't care go to hell",Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
            }
        });

        Button daysBtn = (Button)findViewById(R.id.days_btn);
        daysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> selectedDays = new ArrayList<String>();
                new AlertDialog.Builder(MainActivity.this).setTitle("Select the days please").setMultiChoiceItems(R.array.days,
                        null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                String[] days = getResources().getStringArray(R.array.days);
                                if(b) selectedDays.add(days[i]);
                                else selectedDays.remove(days[i]);
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,selectedDays.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
            }
        });

        Button loginBtn = (Button)findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = getLayoutInflater().inflate(R.layout.dialog_sigin,null);//get the dialog view
                final EditText editText = (EditText)v.findViewById(R.id.user_name);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(v).setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str = editText.getText().toString();
                        Toast.makeText(MainActivity.this,"User " + str + " preformed login",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();
            }
        });
    }

    public class  MyListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(MainActivity.this,"Not Saved",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this,"Make up your mind",Toast.LENGTH_SHORT).show();


            }
        }
    };
    /*@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ALERT_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Save confirmation");
                builder.setMessage("Are you sure you want to save the file?");
                builder.setPositiveButton("Yes, Im am sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"File saved",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No, i don't", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"File not saved",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setCancelable(true);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        Toast.makeText(MainActivity.this,"Dialog dismissed",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);

    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }
*/


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
