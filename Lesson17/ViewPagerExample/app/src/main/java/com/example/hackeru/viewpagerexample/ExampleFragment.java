package com.example.hackeru.viewpagerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackeru.viewpagerexample.enums.ExampleOptions;

/**
 * Created by eran katsav on 20/08/2015.
 */
public class ExampleFragment extends Fragment {

    static public ExampleFragment newInstance(int num) {

        ExampleFragment exampleFragment = new ExampleFragment();
        Bundle args = new Bundle();
        args.putInt("num",num);
        exampleFragment.setArguments(args);
        return exampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView  = (ViewGroup)inflater.inflate(R.layout.fragment_example,container,false);

        ExampleOptions option = ExampleOptions.values()[getArguments().getInt("num")];

        ImageView imageView = (ImageView)rootView.findViewById(R.id.example_image);
        TextView textView = (TextView)rootView.findViewById(R.id.example_text);

        switch (option) {
            case OPT_FRANCE:
                imageView.setImageResource(R.drawable.flag_france);
                textView.setText("France");
                break;
            case OPT_ITALY:
                imageView.setImageResource(R.drawable.flag_italy);
                textView.setText("Italy");
                break;
            case OPT_ISRAEL:
                imageView.setImageResource(R.drawable.flag_israel);
                textView.setText("Israel");
                break;
        }

        return rootView;

    }
}
