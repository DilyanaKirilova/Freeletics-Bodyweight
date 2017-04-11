package com.freeletics.dilyana.freeletics.model.actions;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Exercise extends Action {

    public enum ExerciseName{BYCICLE_CRUNCHES(3), PUSHUPS(5), SITUPS(3.5), SQUATS(3.5);

        private double points;
        private ExerciseName(double points){
            this.points = points;
        }
    }

    private ExerciseName name;
    private String videoUrl;
    protected int repetitions;

    public Exercise(String equipment,int repetitions, ExerciseName name, String videoUrl) {
        super(equipment);

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
}
