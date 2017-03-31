package com.freeletics.dilyana.freeletics.model.actions;

/**
 * Created by Dilyana on 3/31/2017.
 */

public abstract class Action {

    public enum Category{WORKOUT, EXERCISES}

    private String equipment;
    protected Category category;
    protected double points;

    public Action(String equipment) {

        if(equipment != null && !equipment.isEmpty()){
            this.equipment = equipment;
        }
    }
}
