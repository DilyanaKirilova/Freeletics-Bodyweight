package com.freeletics.dilyana.freeletics.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.fragments.CurrentExerciseFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.util.List;

/**
 * Created by Ioana on 05.04.2017 г..
 */

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {

    private List<Workout> workoutList;
    private Context context;

    public WorkoutAdapter(List<Workout> workoutList, Context context) {
        this.workoutList = workoutList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View row = li.inflate(R.layout.workout_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Action workout = workoutList.get(position);

        holder.title.setText(workout.getName().toString());
        holder.duration.setText(workout.getDuration()+"");
        holder.difficulty.setText(workout.getDifficulty()+"");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext() ;
                Bundle bundle = new Bundle();
                bundle.putSerializable("action", workout);
                CurrentExerciseFragment fragment = new CurrentExerciseFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("workout_recview").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView duration;
        private TextView difficulty;
        private LinearLayout layout;

        public MyViewHolder(View row) {
            super(row);
            title = (TextView) row.findViewById(R.id.title_workout);
            duration = (TextView) row.findViewById(R.id.duration_workout);
            difficulty = (TextView) row.findViewById(R.id.difficylty_workout);
            layout = (LinearLayout) row.findViewById(R.id.workout_row);
        }
    }
}
