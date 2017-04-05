package com.freeletics.dilyana.freeletics.model.actions;

import java.util.ArrayList;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Workout extends Action {

    public enum WorkoutName{APHRODITE(500), ARES(345), DIONE(500);

        private double points;
        private WorkoutName(double points){
            this.points = points;
        }
    }
    private WorkoutName name;
    private int duration;
    private int difficulty;
    private int volume;
    int repetitions;
    private ArrayList<String> videoUrl;

    public Workout(String equipment,int volume, WorkoutName name, int duration, int difficulty, ArrayList<String> videoUrl) {
        super(equipment);


        if(repetitions > 0 && repetitions <= 3) {
            this.volume = volume;
        }

        if(name != null){
            this.name = name;
        }

        if(duration > 0 && duration < 3){
            this.duration = duration;
        }

        if(difficulty > 0 && difficulty < 3){
            this.difficulty = difficulty;
        }

        if(videoUrl != null){
            this.videoUrl = videoUrl;
        }

        this.category = Category.WORKOUT;
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
