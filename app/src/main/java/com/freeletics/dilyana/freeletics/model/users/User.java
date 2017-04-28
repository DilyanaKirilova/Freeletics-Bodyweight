package com.freeletics.dilyana.freeletics.model.users;


import android.support.annotation.NonNull;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.ActionsManager;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Ioana on 30.03.2017 г..
 */

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int weight;
    private int height;
    private int age;
    private double points;
    private int id;

    private static int emailCounter =1;

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLogged() {
        return this == UsersManager.getInstance().getLoggedUser();
    }

    public boolean hasNotificationAction(Action action) {

        for(int i = 0; i < 7; i++) {
            for (Action a : this.schedule.get(i)){
                if (a.equals(action)){
                    return true;
                }
            }
        }
        return false;
    }

    public enum Gender {MALE, FEMALE};
    private Gender gender;
    private int picture;//from gallerty or take picture
    private int level = 0;


    private HashMap<ActionsManager.ActionName, TreeSet<Action>> finishedActions;
    private HashMap<Integer, TreeSet<Action>> schedule;

    public User(String firstName, String lastName, int weight, int height, int age, Gender gender, String email, String password) {

        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        } else {
            this.email = emailCounter + "";
            ++emailCounter;

        }
        this.level = 1;

        if (weight > 0) {
            this.weight = weight;
        }

        if (height > 0) {
            this.height = height;
        }
        if (age > 0) {
            this.age = age;
        }
        this.gender = gender;
        this.password = password;
        this.points = 0;
        this.picture = R.drawable.avatar_male;
        this.finishedActions = new HashMap<ActionsManager.ActionName, TreeSet<Action>>();
        this.schedule = new HashMap<Integer, TreeSet<Action>>();
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = 0;
        this.finishedActions = new HashMap<ActionsManager.ActionName, TreeSet<Action>>();
        this.schedule = new HashMap<Integer, TreeSet<Action>>();
    }

    public void addAction(Action action) {

        int day = action.getDay();
        if(!schedule.containsKey(day)){
            schedule.put(day, new TreeSet<Action>());
        }
        schedule.get(day).add(action);
    }

    public List<Action> getSchedule(int day) {

        if(!schedule.containsKey(day)){
            return null;
        }

        List<Action> list = new ArrayList<>();
        list.addAll(schedule.get(day));

        return list;
    }

    public void deleteAction(int day, Action action) {

        if(!schedule.containsKey(day)){
            return;
        }
        schedule.get(day).remove(action);
    }

    public boolean isMale() {
        if(this.gender == null){
            return false;
        }
        return this.gender.equals(Gender.MALE);
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getStringGender() {
        if(this.gender.equals(Gender.FEMALE)){
            return "Female";
        }
        else if(this.gender.equals(Gender.MALE)){
            return "Male";
        }
        return null;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void addFinishedAction(Action a){
        if(a == null) {
            return;
        }
        if(!this.finishedActions.containsKey(a.getName())){
            this.finishedActions.put(a.getName(), new TreeSet<Action>(new ActionTimeComparator()));
        }
        this.finishedActions.get(a.getName()).add(a);
    }

    public List<Action> getWorkouts(ActionsManager.ActionName name){


        if(this.finishedActions.containsKey(name)){
            return new ArrayList<Action> (this.finishedActions.get(name));
        }
        return null;
    }

    public int getPicture() {
        return this.picture;
    }

    public int getLevel() {
        return level;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setLevel() {
        this.level++;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void deleteExcercise(Action a){
        this.finishedActions.remove(a);
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double countBMI(){
        double weight = this.weight;
        double height = this.height;
        double heightInMeters = height/100;
        double bmi = weight/(heightInMeters*heightInMeters);
        return bmi;
    }

    class ActionTimeComparator implements Comparator<Action>{

        @Override
        public int compare(Action o1, Action o2) {
            if(o1.getBestTime() >= o2.getBestTime()){
                return 1;
            }
            return -1;
        }
    }
}
