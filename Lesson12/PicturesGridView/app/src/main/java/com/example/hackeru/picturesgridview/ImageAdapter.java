package com.example.hackeru.picturesgridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by hackeru on 03/08/2015.
 */
public class ImageAdapter extends ArrayAdapter<Integer> {

    public ImageAdapter(Context context, Integer[] ids) {
        super(context,android.R.layout.simple_gallery_item,ids);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView==null)
            imageView = new ImageView(getContext());
        else
            imageView = (ImageView)convertView;
        imageView.setImageResource(getItem(position));
        imageView.setTag(getItem(position));
        return imageView;
    }
}
