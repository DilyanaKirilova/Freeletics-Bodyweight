package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Workout;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProgramFragment extends Fragment {

    private TextView userBMI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_program, container, false);


        userBMI = (TextView) root.findViewById(R.id.users_bmi);
        UsersManager um = UsersManager.getInstance();
        User u = um.getLoggedUser();
        double usersBmi;
        if(u.getHeight()!=0 && u.getWeight()!=0) {
            usersBmi = u.countBMI();
            userBMI.setText("Your BMI is: " + usersBmi+". /n You can start your exercises with perfect repetitions for you!");
        }
        else {
            userBMI.setText("Please add your weight and height in 'SETTINGS' ");
        }


        return root;
    }



}
