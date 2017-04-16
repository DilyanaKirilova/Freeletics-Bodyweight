package com.freeletics.dilyana.freeletics.model.users;


import com.freeletics.dilyana.freeletics.model.actions.Action;

import java.io.Serializable;
import java.util.ArrayList;

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
    private Gender gender;
    private int picture;//from gallerty or take picture
    private int level;
    private ArrayList<Action> workouts;

    public User(String firstName, String lastName, int picture){
        if(firstName!=null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        if(lastName!=null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        this.picture = picture;
    }

    public User(String firstName, String lastName, String email, String password,
                int weight, int height, int age, Gender gender) {
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
    }
    public ArrayList<Action> getWorkouts(){
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
