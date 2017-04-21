package com.freeletics.dilyana.freeletics.adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.fragments.ActionFragment;
import com.freeletics.dilyana.freeletics.fragments.VideoFragment;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;

import java.util.List;

/**
 * Created by Dilyana on 4/17/2017.
 */

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {

    private AppCompatActivity activity;
    private List<Exercise> exercises;

    public ActionAdapter(AppCompatActivity activity, List<Exercise> exercises){

        this.activity = activity;
        this.exercises = exercises;
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View row = inflater.inflate(R.layout.action_row, parent, false);
        return new ActionViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ActionViewHolder holder, int position) {

        final Exercise exercise = exercises.get(position);

        holder.ibImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideoFragment videoFragment = new VideoFragment();
                Bundle bundle = new Bundle();
                String url = exercise.getVideoUrl();
                bundle.putString("url", url);
                videoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, videoFragment).commit();
            }
        });

        holder.tvExerciseName.setText(exercise.getName().toString());
        holder.tvExerciseRepetitions.setText(exercise.getRepetitions()+ "");
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ActionViewHolder extends RecyclerView.ViewHolder {

        ImageButton ibImage;
        TextView tvExerciseName;
        TextView tvExerciseRepetitions;

        public ActionViewHolder(View itemView) {
            super(itemView);

            ibImage               = (ImageButton) itemView.findViewById(R.id.ib_ar_img);
            tvExerciseName        = (TextView) itemView.findViewById(R.id.tv_ar_exercise_name);
            tvExerciseRepetitions = (TextView) itemView.findViewById(R.id.tv_ar_exercise_repetitions);
        }
    }
}
