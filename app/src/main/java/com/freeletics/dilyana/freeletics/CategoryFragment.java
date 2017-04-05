package com.freeletics.dilyana.freeletics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.model.User;


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
        workoutsButton = (Button) root.findViewById(R.id.workouts_button);
        exercisesButton = (Button) root.findViewById(R.id.exercises_button);

        workoutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new fragment
                //Fragment fr=new FregmentN();
                //interface`s instance
                FragmentChange fc=(FragmentChange)getActivity();
                //fc.replaceFragment(fr);
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new fragment
                // Fragment fr=new FregmentN();
                //interface`s instance
                FragmentChange fc=(FragmentChange)getActivity();
                //method in FragmentChangeListener Interface
                //fc.replaceFragment(fr);
            }
        });

        return root;
    }

}
