package com.example.ins.customerdrill;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by ins on 20/07/2015.
 */
public class ShowCustomers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_customers);

        TextView textView = (TextView)findViewById(R.id.custoemrs_amount);

        ArrayList<Customer> customers;
        try  {
            FileInputStream fis = openFileInput(AddCustomerActivity.CUSTOMERFILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>)ois.readObject();
            textView.setText("We have " + customers.size() + " customers!!");
            ois.close();
        }catch (ClassNotFoundException ex) {
            Log.e("customerdrill","some serialization problem",ex);
        }catch (IOException ex) {
            Log.e("customerdrill","No customer file",ex);
            textView.setText("No customers Yet");
        }
    }
}
