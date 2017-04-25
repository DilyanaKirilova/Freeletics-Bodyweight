package com.freeletics.dilyana.freeletics.model.users;


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
    private String motivation;
    private int weight;
    private int height;
    private int age;

    public void addAction(Action action) {

        Day day = action.getDay();
        if(!schedule.containsKey(day)){
            schedule.put(day, new TreeSet<Action>());
        }
        schedule.get(day).add(action);
    }

    public List<Action> getSchedule(Day day) {

        if(!schedule.containsKey(day)){
            return null;
        }

        List<Action> list = new ArrayList<>();
        list.addAll(schedule.get(day));

        return list;
    }

    public void deleteAction(Day day, Action action) {

        if(!schedule.containsKey(day)){
            return;
        }
        schedule.get(day).remove(action);
    }

    public enum BMI {SLIM, NORMAL, FATTENED};
    private BMI bmi;


    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return this.gender;
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

    public enum Gender {MALE, FEMALE};
    public enum Day implements Serializable {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
    private Gender gender;
    private int picture;//from gallerty or take picture
    private int level = 0;
    private ArrayList<Action> workouts;

    private ArrayList<Action> finishedActions;
    private HashMap<Day, TreeSet<Action>> schedule;

    public User(String firstName, String lastName, int picture){
        if(firstName!=null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if(lastName!=null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        this.picture = picture;
        this.finishedActions = new ArrayList<>();
        this.schedule = new HashMap<Day, TreeSet<Action>>();
        UsersManager um = UsersManager.getInstance();
        um.setLoggedUser(this);
    }

    public User(String firstName, String lastName, String email, String password,
                int weight, int height, int age, Gender gender) {
        UsersManager um = UsersManager.getInstance();
        um.setLoggedUser(this);
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        this.level = 1;
        this.workouts = new ArrayList<Action>();

        if(weight > 0) {
            this.weight = weight;
        }

        if(height > 0) {
            this.height = height;
        }
        if(age > 0) {
            this.age = age;
        }
        if(gender != null) {
            this.gender = gender;
        }
        this.finishedActions = new ArrayList<>();
        this.schedule = new HashMap<Day, TreeSet<Action>>();
    }

    public void addFinishedAction(Action a){
        this.finishedActions.add(a);
    }
    public ArrayList<Action> getWorkouts(){
        return workouts;
    }

    public int getPicture() {
        return picture;
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

    public void setBmi(BMI bmi) {
        this.bmi = bmi;
    }

    public BMI getBmi() {
        return bmi;
    }

    public void setLevel() {
        this.level++;
    }


    public double countBMI(){
        String pol = this.getStringGender();
        int weight = this.weight;
        int height = this.height;
        double heightInMeters = height/10;
        int age = this.age;

        double bmi = weight/(heightInMeters*heightInMeters);
        if(bmi<18.5) {
            this.bmi = BMI.SLIM;
        }
        if(bmi>=18.5 && bmi<30){
            this.bmi = BMI.NORMAL;
        }
        if(bmi>=30){
            this.bmi = BMI.FATTENED;
        }
        return bmi;
    }

    public Workout makeProgram(){
        this.countBMI();
        if(this.getStringGender().equals("Female")){

            if(this.bmi == User.BMI.SLIM){
                return new Workout(Workout.WorkoutName.APHRODITE);
            }
            if(this.bmi == User.BMI.NORMAL){
                return new Workout(Workout.WorkoutName.ATHENA);
            }
            if(this.bmi == User.BMI.FATTENED){
                return new Workout(Workout.WorkoutName.KRIOS);
            }
        }
        if(this.getStringGender().equals("Male")){
            if(this.bmi == User.BMI.SLIM){
                return new Workout(Workout.WorkoutName.NYX);
            }
            if(this.bmi == User.BMI.NORMAL){
                return new Workout(Workout.WorkoutName.PERSEPHONE);
            }
            if(this.bmi == User.BMI.FATTENED){
                return new Workout(Workout.WorkoutName.PROMETHEUS);
            }
        }
        return null;
    }
}
