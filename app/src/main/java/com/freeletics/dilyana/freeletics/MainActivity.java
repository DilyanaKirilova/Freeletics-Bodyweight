package com.freeletics.dilyana.freeletics;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.freeletics.dilyana.freeletics.fragments.FragmentLogin;
import com.freeletics.dilyana.freeletics.fragments.SettingsFragment;
import com.freeletics.dilyana.freeletics.fragments.UserInfoFragment;

public class MainActivity extends AppCompatActivity {

    public static CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        callbackManager = CallbackManager.Factory.create();

        if(getIntent().getStringExtra("request_code")!= null){

            if(getIntent().getStringExtra("request_code").equals("start_now")){
                fragmentTransaction.replace(R.id.activity_main, new UserInfoFragment()).commit();

            }

            if(getIntent().getStringExtra("request_code").equals("login")){
                fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).commit();
            }

            if(getIntent().getStringExtra("request_code").equals("settings")) {
                fragmentTransaction.replace(R.id.activity_main, new SettingsFragment()).commit();
            }
        }
    }

    public boolean isEmptyField(String txt, EditText et){
        if (txt.trim().isEmpty()) {
            et.setError("Please fill out this field");
            et.requestFocus();
            return true;
        }
        return false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
