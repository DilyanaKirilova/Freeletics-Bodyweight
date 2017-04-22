package com.freeletics.dilyana.freeletics.model.actions;

import com.freeletics.dilyana.freeletics.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.BURPEES;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.CLIMBERS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.JUMPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.PULLUPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.PUSHUPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.SITUPS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.ExerciseName.SQUATS;
import static com.freeletics.dilyana.freeletics.model.actions.Exercise.noEquipment;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Workout extends Action{

    private static Action burpee   = new Exercise(BURPEES);
    private static Action squat    = new Exercise(SQUATS);
    private static Action situp    = new Exercise(SITUPS);
    private static Action pullup   = new Exercise(PULLUPS);
    private static Action climber  = new Exercise(CLIMBERS);
    private static Action jump     = new Exercise(JUMPS);
    private static Action pushup   = new Exercise(PUSHUPS);

    public enum WorkoutName implements ActionsManager.ActionName{
        APHRODITE   (500, new ArrayList<>(Arrays.asList(burpee, squat, situp)), noEquipment, 2, 2),
        ARES        (345, new ArrayList<>(Arrays.asList(pullup, situp, squat)), "Pullup Bar & 40 m", 1, 3),
        ATHENA      (305, new ArrayList<>(Arrays.asList(climber, situp, squat)), noEquipment, 1, 1),
        DIONE       (500, new ArrayList<>(Arrays.asList(jump, burpee, situp, jump, situp, burpee)), noEquipment, 3, 3),
        KRIOS       (375, new ArrayList<>(Arrays.asList(pullup, squat, situp)), "Pullup Bar", 1, 3),
        METIS       (325, new ArrayList<>(Arrays.asList(burpee, climber, jump)), noEquipment, 1, 3),
        MORPHEUS    (240, new ArrayList<>(Arrays.asList(pushup, situp, jump)), noEquipment, 1, 2),
        NYX         (275, new ArrayList<>(Arrays.asList(situp, burpee, pullup)), noEquipment,1, 1),
        PERSEPHONE  (310, new ArrayList<>(Arrays.asList(situp, burpee, squat)), noEquipment, 1, 2),
        PROMETHEUS  (370, new ArrayList<>(Arrays.asList(climber, pushup, situp, jump)), noEquipment, 2, 2),
        ZEUS        (575, new ArrayList<>(Arrays.asList(pushup, pullup, pushup, situp, squat)), "Pull Bar & Wall", 3, 3);


        private ArrayList<Action> workout;

        private int duration;
        private int difficulty;
        private int repetitions;
        protected double points;
        protected String equipment;


        private WorkoutName(double points, ArrayList<Action> workout, String equipment, int duration, int difficulty){

            this.points = points;
            this.workout = workout;
            this.duration = duration;
            this.difficulty = difficulty;
            this.equipment = equipment;
            this.repetitions = 1;
        }
    }
    private WorkoutName name;


    public Workout(ActionsManager.ActionName name) {

        if(name != null && name instanceof WorkoutName){
            this.name = (WorkoutName) name;
        }
        this.repetitions = this.name.repetitions;
    }

    public int getDuration() {
        return this.name.duration;
    }

    public int getDifficulty() {
        return this.name.difficulty;
    }

    @Override
    public double getPoints() {
        return this.name.points;
    }

    @Override
    public String getEquipment() {
        return this.name.equipment;
    }

    @Override
    public List<Action> getExercises() {
        return this.name.workout;
    }

    @Override
    public String getCategory() {
        return "Workout";
    }

    @Override
    public String getVideoUrl() {
        return null;
    }

    @Override
    public int getRepetitionsList() {
        return R.array.workout_repetitions;
    }


    public ActionsManager.ActionName getName() {
        return name;
    }
}
