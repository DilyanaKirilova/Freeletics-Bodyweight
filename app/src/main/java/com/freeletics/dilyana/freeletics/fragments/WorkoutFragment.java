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
import com.freeletics.dilyana.freeletics.adapters.WorkoutAdapter;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout, container, false);
        context = root.getContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_workout);

        final List<Workout> workouts = new ArrayList<>();
        //workouts.add(new Workout("No equipment", 3, Workout.WorkoutName.APHRODITE, 3.45, 2, ));

        WorkoutAdapter adapter = new WorkoutAdapter(workouts, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        return root;
    }

}
