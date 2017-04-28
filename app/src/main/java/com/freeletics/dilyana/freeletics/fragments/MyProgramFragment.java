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
    private TextView userBMIinfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_program, container, false);


        userBMI = (TextView) root.findViewById(R.id.users_bmi);
        userBMIinfo = (TextView) root.findViewById(R.id.users_bmi_info);
        UsersManager um = UsersManager.getInstance();
        User u = um.getLoggedUser();
        double usersBmi;
        if(u.getHeight()!=0 && u.getWeight()!=0) {
            usersBmi = u.countBMI();
            userBMI.setText("Your BMI is: " + String.format("%.2f", usersBmi));
            userBMIinfo.setText("Your are " + setDescriptionForBMI(usersBmi, u.getStringGender()));
        }
        else {
            userBMI.setText("Please add your weight and height in 'SETTINGS' ");
        }



        return root;
    }

    private String setDescriptionForBMI(double bodyMassIndex, String gender){
       String description=null;
        if(gender.equals("Female")){
           if(bodyMassIndex<19){
               description = "Underweight";
           }
           if(bodyMassIndex>=19 && bodyMassIndex<24){
               description = "Normal weight";
           }
           if(bodyMassIndex>=24 && bodyMassIndex<30){
               description = "Overweight";
           }
           if(bodyMassIndex>=30 && bodyMassIndex<40){
               description = "Obesity";
           }
           if(bodyMassIndex>=40){
               description = "Hard Obesity";
           }
       }
       if(gender.equals("Male")){

           if(bodyMassIndex<20){
               description = "Underweight";
           }
           if(bodyMassIndex>=20 && bodyMassIndex<25){
               description = "Normal weight";
           }
           if(bodyMassIndex>=25 && bodyMassIndex<30){
               description = "Overweight";
           }
           if(bodyMassIndex>=30 && bodyMassIndex<40){
               description = "Obesity";
           }
           if(bodyMassIndex>=40){
               description = "Hard Obesity";
           }
       }

       return description;
    }


}
