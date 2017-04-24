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

    private Workout workout;
    private TextView userBMI;
    private List<Action> workouts = new ArrayList<>();
    private View fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_program, container, false);

        userBMI = (TextView) root.findViewById(R.id.users_bmi);
        fragment = root.findViewById(R.id.fragment);

        Bundle bundle = getArguments();
        Action action = null;
        if (bundle != null) {
            User u = UsersManager.getInstance().getLoggedUser();
            action = (Action) bundle.getSerializable("action");
            workout = (Workout) action;
            userBMI.setText("Your BMI is "+ u.countBMI() +"! Your perfect program is " + workout.getName());
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ActionFragment actionFragment = new ActionFragment();
        actionFragment.setArguments(bundle);
        ft.replace(R.id.fragment, actionFragment).commit();

        //UsersManager um = UsersManager.getInstance();
        //User user = um.getLoggedUser();
       // workout = makeProgram(user);
       // workouts.add(workout);
       // double index = um.countBMI(user);
//        userBMI.setText("Your BMI is "+ index+"! Your perfect program is " + workout.getName());


        return root;
    }



}
