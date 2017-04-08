package com.freeletics.dilyana.freeletics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freeletics.dilyana.freeletics.fragments.CategoryFragment;

public class HomeActivity extends AppCompatActivity implements CategoryFragment.FragmentChange {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void replaceFragment(Fragment f) {
        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_activity, f, f.toString());
        fragmentTransaction.addToBackStack(f.toString());
        fragmentTransaction.commit();
    }
}
