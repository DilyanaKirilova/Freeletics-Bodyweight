package com.freeletics.dilyana.freeletics.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.fragments.CurrentExerciseFragment;
import com.freeletics.dilyana.freeletics.fragments.ExerciseFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.util.List;

/**
 * Created by Ioana on 11.04.2017 г..
 */

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyExerciseViewHolder> {

    private List<Exercise> exerciseList;
    private Context context;

    public ExerciseAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public MyExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View row = li.inflate(R.layout.exercise_row, parent, false);
        MyExerciseViewHolder viewHolder = new MyExerciseViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyExerciseViewHolder holder, final int position) {
        final Action exercise = exerciseList.get(position);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext() ;
                Bundle bundle = new Bundle();
                bundle.putSerializable("action", exercise);
                CurrentExerciseFragment fragment = new CurrentExerciseFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        holder.exerciseName.setText(exercise.getName().toString());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    class MyExerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseName;
        private LinearLayout layout;

        public MyExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseName = (TextView) itemView.findViewById(R.id.exercise_name);
            layout = (LinearLayout) itemView.findViewById(R.id.exercise_row);
        }

    }
}
