package com.example.hackeru.activityresultintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by hackeru on 09/07/2015.
 */
public class Test extends Activity{

    boolean q1,q2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        String name = getIntent().getStringExtra("name");
        TextView textView = (TextView)findViewById(R.id.tester_name);
        textView.setText(name);

        RadioGroup q1RadioGroup= (RadioGroup)findViewById(R.id.rgq1);
        q1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb2q1)
                    q1=true;
                else q1=false;
            }
        });

        RadioGroup q2RadioGroup = (RadioGroup)findViewById(R.id.rgq2);
        q2RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb3q2)
                    q2=true;
                else q2=false;
            }
        });

        Button submit = (Button)findViewById(R.id.submit_test);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int grade=0;
                if(q1) grade+=50;
                if(q2) grade+=50;
                Intent back = new Intent();
                back.putExtra("grade",grade);
                setResult(RESULT_OK,back);
                finish();
            }
        });
    }
}
