package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;
import com.freeletics.dilyana.freeletics.model.actions.Workout;
import com.freeletics.dilyana.freeletics.model.users.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProgramFragment extends Fragment {

    private Exercise exercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_program, container, false);




        return root;
    }

    public void makeProgram(User user){

        if(user.getStringGender().equals("Female")){

            if(user.getBmi() == User.BMI.SLIM){
                //Workout slimWomen = new Workout();
            }
            if(user.getBmi() == User.BMI.NORMAL){

            }
            if(user.getBmi() == User.BMI.FATTENED){

            }
        }
        if(user.getStringGender().equals("Male")){
            if(user.getBmi() == User.BMI.SLIM){

            }
            if(user.getBmi() == User.BMI.NORMAL){

            }
            if(user.getBmi() == User.BMI.FATTENED){

            }
        }

    }

}
