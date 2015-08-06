package com.example.hackeru.contactmanageapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hackeru on 06/08/2015.
 */
public class ContactAdapter implements ListAdapter {

    private Context context;
    private ArrayList<Contact> data;

    public ContactAdapter(Context context, ArrayList<Contact> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.line,null);
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.line_image);
        TextView nameTv = (TextView)view.findViewById(R.id.line_name);
        TextView phoneTv = (TextView)view.findViewById(R.id.phone_number);

        Contact contact = (Contact)getItem(i);

        imageView.setImageBitmap(contact.getPicture());
        nameTv.setText(contact.getName());
        phoneTv.setText(contact.getPhoneNumber());

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
