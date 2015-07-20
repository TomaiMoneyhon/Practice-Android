package com.example.ins.customerdrill;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by ins on 20/07/2015.
 */
public class AddCustomerActivity extends Activity {

    static final String CUSTOMERFILE = "customers";
    ArrayList<Customer> customers;
    EditText name,age;
    RadioButton male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);

        name = (EditText)findViewById(R.id.customer_name);
        age = (EditText)findViewById(R.id.customer_age);
        male = (RadioButton)findViewById(R.id.male);

        Button add = (Button)findViewById(R.id.save_customer);

        FileInputStream fis;
        ObjectInputStream ois=null;
        try {
            fis = openFileInput(CUSTOMERFILE);
            ois = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>)ois.readObject();
        }
        catch (ClassNotFoundException ex) {
            Log.e("CustomerDrill","serialization probelm",ex);
        }
        catch (IOException ex) {
            Log.e("CustomerDrill","No customer file found",ex);
            customers = new ArrayList<Customer>();
        }
        finally {
            try{
                ois.close();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                int ageNum = Integer.parseInt(age.getText().toString());
                boolean isMale = male.isChecked();
                customers.add(new Customer(nameStr,ageNum,isMale));
                name.setText("");
                age.setText("");
                male.setChecked(true);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        FileOutputStream fos;
        ObjectOutputStream ous=null;
        try {
            fos = openFileOutput(CUSTOMERFILE,Activity.MODE_PRIVATE);
            ous = new ObjectOutputStream(fos);
            ous.writeObject(customers);
        }catch (IOException ex) {
            Log.e("CustomerDrill - add","some io problem saving",ex);
        }
        finally {
            try {
                ous.close();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
