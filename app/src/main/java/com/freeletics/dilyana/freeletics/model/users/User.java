package com.freeletics.dilyana.freeletics.model.users;

import com.freeletics.dilyana.freeletics.model.actions.Action;

import java.util.ArrayList;

/**
 * Created by Ioana on 30.03.2017 г..
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motivation;
    private int weight;
    private int height;
    private int age;
    public enum Gender {MALE, FEMALE};
    private Gender gender;
    private int picture;//from gallerty or take picture
    private int level;
    private ArrayList<Action> workouts;

    public User(String firstName, String lastName, String email, String password,
                int weight, int height, int age, Gender gender) {
        if(firstName!=null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if(lastName!=null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        if(email!=null && !email.isEmpty()) {
            this.email = email;
        }
        if(password!=null && !password.isEmpty()) {
            this.password = password;
        }
        this.level = 1;
        this.workouts = new ArrayList<Action>();
      //  this.weight = weight; //SeekBar
       // this.height = height; // SeekBar
       // if(age>0) {
         //   this.age = age;
        //
        //this.gender = gender;
    }

    public ArrayList<Action> getWorkouts() {
        return workouts;
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
}
