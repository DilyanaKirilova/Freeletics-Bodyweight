package com.freeletics.dilyana.freeletics.fragments;


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

import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.CRUNCHES;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.PUSHUPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.SITUPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.SQUATS;

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
        exercises.add(new Exercise(CRUNCHES));
        exercises.add(new Exercise(PUSHUPS));
        exercises.add(new Exercise(SITUPS));
        exercises.add(new Exercise(SQUATS));


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exercises, context);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        return root;
    }

}
