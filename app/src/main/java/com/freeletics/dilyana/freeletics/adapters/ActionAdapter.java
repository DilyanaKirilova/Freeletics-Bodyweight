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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.dialog_fragments.VerificationFragment;
import com.freeletics.dilyana.freeletics.fragments.ActionFragment;
import com.freeletics.dilyana.freeletics.fragments.VideoFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dilyana on 4/17/2017.
 */

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {

    private AppCompatActivity activity;
    private List<Action> exercises;

    public ActionAdapter(AppCompatActivity activity, List<Action> exercises){

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

        final Action exercise = exercises.get(position);

        if(exercises.get(position).getTime() != null){
            holder.tvExerciseTime.setText(exercise.getTime());

            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    VerificationFragment verificationFragment = new VerificationFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("action", exercise);
                    verificationFragment.setArguments(bundle);
                    verificationFragment.show(activity.getSupportFragmentManager(), "verificationFragment");
                    return false;
                }
        });
        }
        else {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    VideoFragment videoFragment = new VideoFragment();
                    Bundle bundle = new Bundle();
                    String url = exercise.getVideoUrl();
                    bundle.putString("url", url);
                    videoFragment.setArguments(bundle);
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, videoFragment).commit();
                }
            });
        }
        holder.tvExerciseName.setText(exercise.getName().toString());
        holder.tvExerciseRepetitions.setText(exercise.getRepetitions());
    }

    @Override
    public int getItemCount() {
        if(exercises == null){
            return 0;
        }
        return exercises.size();
    }

    public class ActionViewHolder extends RecyclerView.ViewHolder {

        ImageButton ibImage;
        TextView tvExerciseName;
        TextView tvExerciseRepetitions;
        TextView tvExerciseTime;
        LinearLayout layout;

        public ActionViewHolder(View itemView) {
            super(itemView);

            ibImage               = (ImageButton) itemView.findViewById(R.id.ib_ar_img);
            tvExerciseName        = (TextView) itemView.findViewById(R.id.tv_ar_exercise_name);
            tvExerciseRepetitions = (TextView) itemView.findViewById(R.id.tv_ar_exercise_repetitions);
            tvExerciseTime        = (TextView) itemView.findViewById(R.id.tv_ar_action_time);
            layout                = (LinearLayout) itemView.findViewById(R.id.action_row);
        }
    }
}
