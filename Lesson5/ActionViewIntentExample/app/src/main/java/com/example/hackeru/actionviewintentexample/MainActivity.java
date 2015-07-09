package com.example.hackeru.actionviewintentexample;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button email_btn = (Button)findViewById(R.id.send_mail_btn);
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"asafr@walla.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"this is the subject");
                intent.putExtra(Intent.EXTRA_TEXT,"this is the email body");
                startActivity(Intent.createChooser(intent,"Send Email with"));
            }
        });

        Button mapButton = (Button)findViewById(R.id.map_btn);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGoogleMaps(32.0833,34.8167,"Ramat Gan");
            }
        });

        Button video = (Button)findViewById(R.id.video_btn);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "STxF_gePVxE";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v="+id));
                startActivity(intent);
            }
        });

        Button call = (Button)findViewById(R.id.phone_btn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"+"5222222"));
                startActivity(intent);
            }
        });

        Button browse = (Button)findViewById(R.id.browse_btn);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.site_url);
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });

        Button contacts = (Button)findViewById(R.id.contact_btn);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI,String.valueOf(1));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void launchGoogleMaps(double latitude, double longitude, String label) {
        String format = "geo:0,0?q="+latitude+","+longitude+"("+label+")";
        Uri uri = Uri.parse(format);
        Intent intent  = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
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
