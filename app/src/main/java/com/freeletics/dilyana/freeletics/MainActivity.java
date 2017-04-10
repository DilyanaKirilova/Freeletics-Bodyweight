package com.freeletics.dilyana.freeletics;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freeletics.dilyana.freeletics.fragments.FragmentLogin;
import com.freeletics.dilyana.freeletics.fragments.UserInfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getIntent().getStringExtra("request_code")!= null){

            if(getIntent().getStringExtra("request_code").equals("start_now")){
                fragmentTransaction.replace(R.id.activity_main, new UserInfoFragment()).commit();

            }

            if(getIntent().getStringExtra("request_code").equals("login")){
                fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).commit();
            }

            if(getIntent().getStringExtra("request_code").equals("settings")) {
                fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).commit();
            }
        }
    }
}
