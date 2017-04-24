package com.freeletics.dilyana.freeletics.model.actions;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dilyana on 16.4.2017 г..
 */

public abstract class Action implements Serializable, Comparable<Action>{

    protected ActionsManager.ActionName name;
    private double points;
    private String equipment;
    protected int repetitions;
    private Calendar calendar;
    private boolean isEvent;
    private boolean hasNotification;

    public Action(){
        this.points = 0.0;
        this.equipment = null;
        this.repetitions = 0;
        this.calendar = Calendar.getInstance();
        this.calendar.set(Calendar.HOUR_OF_DAY, -1);
        this.calendar.set(Calendar.MINUTE, -1);
        this.calendar.set(Calendar.DAY_OF_WEEK, -1);
        this.isEvent = false;
        this.hasNotification = false;
    }

    public abstract ActionsManager.ActionName getName();

    public abstract int getDuration();

    public abstract int getDifficulty();

    public abstract double getPoints();

    public abstract String getEquipment();

    public abstract List<Action> getExercises();

    public abstract String getCategory();

    public String getRepetitions() { return String.valueOf(this.repetitions); }

    public int getHour(){
        return this.calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute(){
        return this.calendar.get(Calendar.MINUTE);
    }

    public int getDay(){
        return this.calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void setDay(int day) {
        this.calendar.set(Calendar.DAY_OF_WEEK, day);
    }

    public void setHour(int hour) {
        this.calendar.set(Calendar.HOUR_OF_DAY, hour);
    }

    public void setMinute(int minute) {
        this.calendar.set(Calendar.MINUTE, minute);
    }


    @Override
    public int compareTo(Action o) {

        if(this.calendar.get(Calendar.HOUR_OF_DAY) == o.calendar.get(Calendar.HOUR_OF_DAY)){
            return this.calendar.get(Calendar.MINUTE) - o.calendar.get(Calendar.MINUTE);
        }
        return this.calendar.get(Calendar.HOUR_OF_DAY) - o.calendar.get(Calendar.HOUR_OF_DAY);
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public abstract String getVideoUrl();

    public abstract int getRepetitionsList();

    public void setAsEvent() {
        this.isEvent = true;
    }

    public boolean isEvent() {
        return this.isEvent;
    }

    public void setNotification(boolean notification) {
        this.hasNotification = notification;
    }

    public boolean hasNotification() {
        return this.hasNotification;
    }
}
