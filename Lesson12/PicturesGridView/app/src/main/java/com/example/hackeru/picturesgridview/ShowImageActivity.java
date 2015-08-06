package com.example.hackeru.picturesgridview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by hackeru on 03/08/2015.
 */
public class ShowImageActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image);
        ImageView imageView = (ImageView)findViewById(R.id.image);
        imageView.setImageResource(getIntent().getIntExtra("image_id",R.drawable.flag_israel));
    }
}
