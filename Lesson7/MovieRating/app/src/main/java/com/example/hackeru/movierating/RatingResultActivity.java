package com.example.hackeru.movierating;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by hackeru on 16/07/2015.
 */
public class RatingResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_result);

        TextView name  = (TextView)findViewById(R.id.movie_name_result);
        TextView isFiction = (TextView)findViewById(R.id.is_science_fiction_result);
        TextView rating = (TextView)findViewById(R.id.rating_result);

        SharedPreferences sharedPreferences = getSharedPreferences("details",Activity.MODE_PRIVATE);
        name.setText(sharedPreferences.getString("movie_name",""));
        isFiction.setText("The movie " + (sharedPreferences.getBoolean("is_fiction",false) ? "is" : "isn't") + " science fiction");
        rating.setText("And the score is : " + sharedPreferences.getFloat("rating",0.0f));

    }
}
