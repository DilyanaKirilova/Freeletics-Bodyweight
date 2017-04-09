package com.freeletics.dilyana.freeletics;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getStringExtra("request_code")!= null){

            if(getIntent().getStringExtra("request_code").equals("start_now")){
                //TODO go to Register fragment
            }

            if(getIntent().getStringExtra("request_code").equals("login")){
                //TODO go to Login fragment
            }
        }
    }
}
