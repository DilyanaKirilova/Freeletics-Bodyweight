package com.freeletics.dilyana.freeletics.model.actions;

import java.util.ArrayList;
import java.util.Arrays;

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

public class Workout implements Action{

    private static Exercise burpee   = new Exercise(BURPEES);
    private static Exercise squat    = new Exercise(SQUATS);
    private static Exercise situp    = new Exercise(SITUPS);
    private static Exercise pullup   = new Exercise(PULLUPS);
    private static Exercise climber  = new Exercise(CLIMBERS);
    private static Exercise jump     = new Exercise(JUMPS);
    private static Exercise pushup   = new Exercise(PUSHUPS);



    public enum WorkoutName{
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


        private double points;
        private ArrayList<Exercise> workout;
        private String equipment;
        private int duration;
        private int difficulty;

        private WorkoutName(double points, ArrayList<Exercise> workout, String equipment, int duration, int difficulty){
            this.points = points;
            this.workout = workout;
            this.equipment = equipment;
            this.duration = duration;
            this.difficulty = difficulty;
        }
    }
    private WorkoutName name;
    private int volume;


    public Workout(WorkoutName name) {

        if(name != null){
            this.name = name;
        }
        this.volume = 1;
    }

    public int getDuration() {
        return this.name.duration;
    }

    public int getDifficulty() {
        return this.name.difficulty;
    }

    public WorkoutName getName() {
        return name;
    }
}
