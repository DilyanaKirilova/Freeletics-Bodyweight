package com.freeletics.dilyana.freeletics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.util.List;

/**
 * Created by Ioana on 11.04.2017 г..
 */

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyExerciseViewHolder>{

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
    public void onBindViewHolder(MyExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        if(exercise.getName() == Exercise.ExerciseName.SITUPS){
            holder.excercisesButton.setText("SITUPS");
        }
        if(exercise.getName() == Exercise.ExerciseName.SQUATS){
            holder.excercisesButton.setText("SQUATS");
        }
        if(exercise.getName() == Exercise.ExerciseName.PUSHUPS){
            holder.excercisesButton.setText("PUSHUPS");
        }
        if(exercise.getName() == Exercise.ExerciseName.BYCICLE_CRUNCHES){
            holder.excercisesButton.setText("BYCICLE CRUNCHES");
        }
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }



    class MyExerciseViewHolder extends RecyclerView.ViewHolder{

        private Button excercisesButton;
        private View itemView;

        public MyExerciseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            excercisesButton = (Button) itemView.findViewById(R.id.excercise_button);

        }
    }
}
