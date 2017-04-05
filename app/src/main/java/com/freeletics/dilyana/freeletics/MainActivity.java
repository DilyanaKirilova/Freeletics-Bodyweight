package com.freeletics.dilyana.freeletics;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freeletics.dilyana.freeletics.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //keytool -exportcert -alias androiddebugkey -keystore [path-to-users-directory]\.android\debug.keystore" | openssl sha1 -binary | openssl base64


    private static ArrayList<User> registerUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentLogin fragmentLogin = (FragmentLogin) fm.findFragmentById(R.id.fragment_login);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_main, new FragmentLogin(), "Login");
        ft.commit();
    }

    public static ArrayList<User> getRegisterUsers() {
        return registerUsers;
    }
}
