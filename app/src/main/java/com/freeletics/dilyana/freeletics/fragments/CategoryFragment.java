package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    private Button workoutsButton;
    private Button exercisesButton;


    public interface FragmentChange{
        public void replaceFragment(Fragment f);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        Button btnWorkout = (Button) root.findViewById(R.id.workouts_button);
        Button btnExercise = (Button) root.findViewById(R.id.exercises_button);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction.replace(R.id.fragment_container, new WorkoutFragment());
            }
        });

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO go to ExerciseFragment
            }
        });

        return root;
    }

}
