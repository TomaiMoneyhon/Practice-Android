package com.example.hackeru.fragmentexample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hackeru on 20/08/2015.
 */
public class RegisterFragment extends Fragment {

    interface OnRegisterFragmentListener { //Create the interface
        void onRegisterButtonClicked(String password);
    }

    OnRegisterFragmentListener mCallBack; //ref to interface implementation

    public static RegisterFragment newInstance(String userName) {
        RegisterFragment registerFragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString("user_name",userName);
        registerFragment.setArguments(args);
        return registerFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Toast.makeText(activity,"on attach",Toast.LENGTH_SHORT).show();

        try {
            mCallBack = (OnRegisterFragmentListener)activity; //the activity who attached the fragment will implement the interface
        }catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement OnRegisterFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(),"on create",Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"on create view",Toast.LENGTH_SHORT).show();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.regiter_fragment,container,false);

        TextView textView = (TextView)rootView.findViewById(R.id.user_name_output);
        String userName = getArguments().getString("user_name");
        textView.setText(userName);

        final EditText passwordEt = (EditText)rootView.findViewById(R.id.password_input);
        Button register = (Button)rootView.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = passwordEt.getText().toString();
                mCallBack.onRegisterButtonClicked(password);
               // getFragmentManager().beginTransaction().remove(RegisterFragment.this).commit(); //the fragment can remove himself
            }
        });

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(),"on start",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(),"on resume",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(),"on pause",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(),"on stop",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(),"on destroty",Toast.LENGTH_SHORT).show();

    }
}
