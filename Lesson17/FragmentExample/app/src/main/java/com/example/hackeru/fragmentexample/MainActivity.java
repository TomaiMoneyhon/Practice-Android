package com.example.hackeru.fragmentexample;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnRegisterFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userNameEt = (EditText)findViewById(R.id.user_name_input);
        Button register = (Button)findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = userNameEt.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.root_layout, RegisterFragment.newInstance(userName), "register_frag").commit();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRegisterButtonClicked(String password) {
        TextView textView = (TextView)findViewById(R.id.password_output);
        textView.setText(password);
        Fragment fragmentToRemove = getFragmentManager().findFragmentByTag("register_frag");
        getFragmentManager().beginTransaction().remove(fragmentToRemove).commit();

    }
}
