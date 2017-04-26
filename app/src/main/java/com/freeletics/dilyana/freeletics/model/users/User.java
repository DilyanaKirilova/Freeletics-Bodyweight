package com.freeletics.dilyana.freeletics.model.users;


import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Workout;

import java.io.Serializable;
import java.util.ArrayList;
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
    private int id;
    private static int emailCounter =1;

    public void setId(int id) {
        this.id = id;
    }

    public enum Gender {MALE, FEMALE};
    private Gender gender;
    private int picture;//from gallerty or take picture
    private int level = 0;


    private ArrayList<Action> finishedActions;
    private HashMap<Integer, TreeSet<Action>> schedule;

    public User(String firstName, String lastName, int weight, int height, int age, Gender gender, String email, String password) {

        if(firstName!=null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if(lastName!=null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        else{
            this.email = emailCounter +"";
            ++emailCounter;

        }
        this.level = 1;

        if(weight > 0) {
            this.weight = weight;
        }

        if(height > 0) {
            this.height = height;
        }
        if(age > 0) {
            this.age = age;
        }
        this.gender = gender;
        this.password = password;

        this.picture = R.drawable.avatar_male;
        this.finishedActions = new ArrayList<>();
        this.schedule = new HashMap<Integer, TreeSet<Action>>();
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        if(a != null) {
            this.finishedActions.add(a);
        }
    }
    public ArrayList<Action> getWorkouts(){
        return this.finishedActions;
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



    public double countBMI(){
        int weight = this.weight;
        double height = this.height;
        double heightInMeters = height/10;
        double bmi = weight/(heightInMeters*heightInMeters);
        return bmi;
    }


}
