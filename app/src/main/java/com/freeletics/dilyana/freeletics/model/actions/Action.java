package com.freeletics.dilyana.freeletics.model.actions;

import com.freeletics.dilyana.freeletics.model.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dilyana on 16.4.2017 г..
 */

public abstract class Action implements Serializable, Comparable<Action>{

    protected ActionsManager.ActionName name;
    protected String time;
    protected double points;
    protected String equipment;
    protected int repetitions;
    protected User.Day day;

    public Action(){

        this.time = null;
        this.points = 0.0;
        this.equipment = null;
        this.repetitions = 0;
        this.day = null;
    }

    public abstract ActionsManager.ActionName getName();

    public abstract int getDuration();

    public abstract int getDifficulty();

    public abstract double getPoints();

    public abstract String getEquipment();

    public abstract List<Action> getExercises();

    public abstract String getCategory();

    public String getRepetitions() { return String.valueOf(this.repetitions); }

    public String getTime(){
        return this.time;
    }

    @Override
    public int compareTo(Action o) {
        return this.time.compareTo(o.time);
    }

    public void setTime(String time) {
        
        if(time != null) {
            this.time = time;
        }
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public abstract String getVideoUrl();

    public User.Day getDay() {
        return this.day;
    }

    public void setDay(User.Day day) {
        this.day = day;
    }

    public abstract int getRepetitionsList();
}
