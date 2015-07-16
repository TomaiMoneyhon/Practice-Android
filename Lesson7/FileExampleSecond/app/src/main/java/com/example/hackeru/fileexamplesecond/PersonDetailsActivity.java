package com.example.hackeru.fileexamplesecond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by hackeru on 16/07/2015.
 */
public class PersonDetailsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_details);

        TextView firstNameTV = (TextView)findViewById(R.id.first_name_output);
        TextView lastNameTV = (TextView)findViewById(R.id.last_name_output);

        Intent intent = getIntent();
        String fileName  = intent.getStringExtra("filename");
        Person person = Person.readFromFile(this,fileName);

        firstNameTV.setText(person.getfName());
        lastNameTV.setText(person.getlName());

    }
}
