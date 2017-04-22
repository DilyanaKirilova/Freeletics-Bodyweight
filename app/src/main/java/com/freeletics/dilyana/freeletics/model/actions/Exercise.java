package com.freeletics.dilyana.freeletics.model.actions;

import com.freeletics.dilyana.freeletics.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Exercise extends Action{

    private static String burpee       = "https://www.youtube.com/watch?v=RLTxXfh7w4c&index=1&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM";
    private static String climber      = "https://www.youtube.com/watch?v=u_jJpx6d40s&index=2&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM";
    private static String jump         = "https://www.youtube.com/watch?v=ggqcxKApjns&index=4&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM";
    private static String squat        = "https://www.youtube.com/watch?v=vaZ6l8a4JTw&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM&index=8";
    private static String situp        = "https://www.youtube.com/watch?v=r2vzpSdAIsA&index=7&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM";
    private static String pullup       = "https://www.youtube.com/watch?v=BlF0Uh3uhxU&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM&index=38";
    private static String crunch       = "https://www.youtube.com/watch?v=RtDWzg9ESRA&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM&index=11";
    private static String frogger      = "https://www.youtube.com/watch?v=nddYuZP-kSo&index=26&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM";
    private static String pushup       = "https://www.youtube.com/watch?v=Kfu8SKWr1ts&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM&index=39";
    private static String standups     = "https://www.youtube.com/watch?v=PY5l4wswlig&list=PLVxsEE_JzUPGiaincSAKSbYVSU_YJLhuM&index=33";
    static String noEquipment = "No Equipment";

    public enum ExerciseName implements ActionsManager.ActionName{
        BURPEES(6, burpee, noEquipment, 10),
        CLIMBERS(3.5, climber, noEquipment, 10),
        JUMPS(8, jump, noEquipment, 10),
        SQUATS(3.5, squat, noEquipment, 10),
        SITUPS(3.5, situp, noEquipment, 10),
        PULLUPS(5, pullup, "Pullup Bar", 10),
        CRUNCHES(6, crunch, noEquipment, 10),
        FROGGERS(5, frogger, noEquipment, 10),
        PUSHUPS(5, pushup, noEquipment, 10),
        STANDUPS(3, standups, noEquipment, 10);


        private String videoUrl;
        protected double points;
        protected String equipment;
        protected int repetitions;

        private ExerciseName (double points, String videoUrl, String equipment, int repetitions){
            this.points = points;
            this.videoUrl = videoUrl;
            this.equipment = equipment;
            this.repetitions = repetitions;
        }

        public double getPoints() {
            return points;
        }
    }

    private ExerciseName name;

    public Exercise(ActionsManager.ActionName name) {
        if(name != null && name instanceof ExerciseName){
            this.name = (ExerciseName) name;
        }
        this.repetitions = this.name.repetitions;
    }

    public ActionsManager.ActionName getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getDifficulty() {
        return 0;
    }

    public double getPoints(){
        return this.name.points;
    }

    public String getVideoUrl(){
        return this.name.videoUrl;
    }

    @Override
    public int getRepetitionsList() {
        return R.array.exercise_repetitions;
    }

    public String getEquipment() {
        return this.name.equipment;
    }

    @Override
    public List<Action> getExercises() {
        Exercise e = new Exercise(this.name);
        List<Action> exercise = new ArrayList<>();
        exercise.add(e);
        return exercise;
    }

    @Override
    public String getCategory() {
        return "Exercise";
    }
}
