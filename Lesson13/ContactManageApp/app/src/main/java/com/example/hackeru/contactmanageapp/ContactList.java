package com.example.hackeru.contactmanageapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by hackeru on 06/08/2015.
 */
public class ContactList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

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
        if(contacts==null) contacts = new ArrayList<>();

        ContactAdapter adapter = new ContactAdapter(this,contacts);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this,ContactDisplay.class);
        intent.putExtra("index",position);
        startActivity(intent);
    }
}
