package com.freeletics.dilyana.freeletics.model.actions;

import java.io.Serializable;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Exercise extends Action implements Serializable{

    public enum ExerciseName{BYCICLE_CRUNCHES(3), PUSHUPS(5), SITUPS(3.5), SQUATS(3.5);

        private double points;
        private ExerciseName(double points){
            this.points = points;
        }

        public double getPoints() {
            return points;
        }
    }

    private ExerciseName name;
    private String videoUrl;
    protected int repetitions;
    private int picture;

    public Exercise(String equipment,int repetitions, ExerciseName name, String videoUrl, int picture) {
        super(equipment);
        this.picture = picture;
        if(repetitions > 0 && repetitions <= 2000) {
            this.repetitions = repetitions;
        }

        if(name != null){
            this.name = name;
        }

        if(videoUrl != null && !videoUrl.isEmpty()){
            this.videoUrl = videoUrl;
        }

        this.category = Category.EXERCISES;
        this.points = this.name.points;

    }

    public ExerciseName getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }
}
