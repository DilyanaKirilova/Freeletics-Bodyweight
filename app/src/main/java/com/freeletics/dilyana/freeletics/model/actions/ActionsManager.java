package com.freeletics.dilyana.freeletics.model.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dilyana on 4/17/2017.
 */
public class ActionsManager {
    private static ActionsManager ourInstance = new ActionsManager();

    public static ActionsManager getInstance() {
        return ourInstance;
    }

    List<Exercise> exercises = new ArrayList<>();
    List<Workout> workouts = new ArrayList<>();

    private ActionsManager() {

        for (Exercise.ExerciseName exerciseName : Exercise.ExerciseName.values()) {
            exercises.add(new Exercise(exerciseName));
        }

        for (Workout.WorkoutName workoutName : Workout.WorkoutName.values()) {
            workouts.add(new Workout(workoutName));
        }
    }

    public List<Exercise> getExercises() {
        return Collections.unmodifiableList(exercises);
    }

    public List<Workout> getWorkouts() {
        return Collections.unmodifiableList(workouts);
    }
}
