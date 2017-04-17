package com.freeletics.dilyana.freeletics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
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
        Workout workout = workoutList.get(position);

        holder.title.setText(workout.getName().toString());

        holder.duration.setText(workout.getDuration()+"");
        holder.difficylty.setText(workout.getDifficulty()+"");

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private View row;
        private TextView title;
        private TextView description;
        private TextView duration;
        private TextView difficylty;

        public MyViewHolder(View row) {
            super(row);
            this.row = row;
            title = (TextView) row.findViewById(R.id.title_workout);
            description = (TextView) row.findViewById(R.id.descr_workout);
            duration = (TextView) row.findViewById(R.id.duration_workout);
            difficylty = (TextView) row.findViewById(R.id.difficylty_workout);
        }
    }
}
