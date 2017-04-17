package com.freeletics.dilyana.freeletics.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.adapters.ExerciseAdapter;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_exercise, container, false);

        context = root.getContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_exercise);
        final List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("No equipment", 10, Exercise.ExerciseName.BYCICLE_CRUNCHES, " ", R.drawable.bicycle_crunches));
        exercises.add(new Exercise("No equipment", 10, Exercise.ExerciseName.PUSHUPS, " ", R.drawable.pushups));
        exercises.add(new Exercise("No equipment", 10, Exercise.ExerciseName.SITUPS, " ", R.drawable.situps));
        exercises.add(new Exercise("No equipment", 10, Exercise.ExerciseName.SQUATS, " ", R.drawable.squats));


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exercises, context);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        return root;
    }

}
