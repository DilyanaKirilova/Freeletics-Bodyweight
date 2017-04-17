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


    private static ArrayList<Exercise> aphrodite = new ArrayList<>(Arrays.asList(burpee, squat, situp));
    private static ArrayList<Exercise> ares      = new ArrayList<>(Arrays.asList(pullup, situp, squat));
    private static ArrayList<Exercise> athena    = new ArrayList<>(Arrays.asList(climber, situp, squat));
    private static ArrayList<Exercise> dione     = new ArrayList<>(Arrays.asList(jump, burpee, situp, jump, situp, burpee));
    private static ArrayList<Exercise> krios     = new ArrayList<>(Arrays.asList(pullup, squat, situp));
    private static ArrayList<Exercise> metis     = new ArrayList<>(Arrays.asList(burpee, climber, jump));
    private static ArrayList<Exercise> morpheus  = new ArrayList<>(Arrays.asList(pushup, situp, jump));
    private static ArrayList<Exercise> nyx       = new ArrayList<>(Arrays.asList(situp, burpee, pullup));
    private static ArrayList<Exercise> persephone  = new ArrayList<>(Arrays.asList(situp, burpee, squat));
    private static ArrayList<Exercise> prometheus  = new ArrayList<>(Arrays.asList(climber, pushup, situp, jump));
    private static ArrayList<Exercise> zeus  = new ArrayList<>(Arrays.asList(pushup, pullup, pushup, situp, squat));


    public enum WorkoutName{APHRODITE(500, aphrodite, noEquipment, 2, 2),
        ARES(345,ares, "Pullup Bar & 40 m", 1, 3),
        ATHENA(305, athena, noEquipment, 1, 1),
        DIONE(500, dione, noEquipment, 3, 3),
        KRIOS(375, krios, "Pullup Bar", 1, 3),
        METIS(325, metis, noEquipment, 1, 3),
        MORPHEUS(240, morpheus, noEquipment, 1, 2),
        NYX(275, nyx, noEquipment,1, 1),
        PERSEPHONE(310, persephone, noEquipment, 1, 2),
        PROMETHEUS(370, prometheus, noEquipment, 2, 2),
        ZEUS(575, zeus, "Pull Bar & Wall", 3, 3);


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
    private int duration;
    private int difficulty;
    private int volume;
    private double points;
    private Exercise firstExercise;
    private Exercise secExercise;
    private Exercise thirdExercise;
    private String equipment;


    public Workout(WorkoutName name) {

        if(name != null){
            this.name = name;
        }
        this.volume = 1;
        this.points = this.name.points;
    }

    public int getDuration() {
        return duration;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public WorkoutName getName() {
        return name;
    }
}
