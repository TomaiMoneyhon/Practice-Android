package com.example.hackeru.contactmanageapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by hackeru on 06/08/2015.
 */
public class ContactDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_display);

        ImageView imageView = (ImageView)findViewById(R.id.image);
        TextView nameTv = (TextView)findViewById(R.id.name);
        TextView phoneTv = (TextView)findViewById(R.id.phone);
        TextView emailTv = (TextView)findViewById(R.id.email);
        TextView addressTv = (TextView)findViewById(R.id.address);
        TextView webTv = (TextView)findViewById(R.id.site);
        TextView birthTv = (TextView)findViewById(R.id.birth);
        TextView timeTv = (TextView)findViewById(R.id.time);
        TextView daysTv = (TextView)findViewById(R.id.days);

        ArrayList<Contact> contacts=null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput("contacts");
            ois = new ObjectInputStream(fis);
            contacts = (ArrayList<Contact>) ois.readObject();
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

        if(contacts!=null) {

            final Contact contact = contacts.get(getIntent().getIntExtra("index", 0));
            imageView.setImageBitmap(contact.getPicture());
            nameTv.setText(contact.getName());
            phoneTv.setText(contact.getPhoneNumber());
            addressTv.setText(contact.getAddress());
            emailTv.setText(contact.getEmail());
            webTv.setText(contact.getWebSite());
            birthTv.setText(contact.getBirthDate());
            timeTv.setText(contact.getTimeToCall());
            daysTv.setText(contact.getDays());

            Button mapBtn = (Button)findViewById(R.id.map_btn);
            mapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mapStr = "geo:0,0?q=" + contact.getAddress();
                    Uri uri = Uri.parse(mapStr);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            });
        }
    }
}
