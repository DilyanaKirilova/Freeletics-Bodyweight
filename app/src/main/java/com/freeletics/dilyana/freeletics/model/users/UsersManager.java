package com.freeletics.dilyana.freeletics.model.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Dilyana on 8.4.2017 г..
 */

public class UsersManager {
    private static final UsersManager ourInstance = new UsersManager();


    public static UsersManager getInstance() {
        return ourInstance;
    }

    private HashMap<String, User> registeredUsers;
    private User loggedUser;

    private UsersManager() {
        this.registeredUsers = new HashMap<String, User>();
    }

    public boolean existsUser(String email) {

        if (!this.registeredUsers.containsKey(email)) {
            return false;
        }
        return true;
    }

    public void registerUser(String firstName, String lastName, String email, String password,
                             int weight, int height, int age, User.Gender gender) {

        User user = new User(firstName, lastName, email, password,
                weight, height, age, gender);

        if (!this.registeredUsers.containsKey(email)) {
            this.registeredUsers.put(email, user);
        }
        this.loggedUser = user;
    }

    public boolean isValidLogin(String email, String password) {

        if (registeredUsers.containsKey(email)) {
            User user = registeredUsers.get(email);
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                this.loggedUser = user;
                return true;
            }
        }
        return false;
    }

    public void logOutUser() {
        this.loggedUser = null;
    }

    public void deleteUserRegistration() {
        this.registeredUsers.remove(loggedUser.getEmail());
        this.loggedUser = null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void changeLoggedUserPassword(String newPassword) {

        if(newPassword == null || newPassword.trim().isEmpty()){
            return;
        }
        UsersManager.getInstance().getLoggedUser().setPassword(newPassword);
        registeredUsers.get(loggedUser.getEmail()).setPassword(newPassword);
    }


    public void setLoggedUser(User user){
        if(user != null){
            this.loggedUser = user;
        }
    }

    public void updateUserInfo(String firstNameStr, String lastNameStr, String emailStr, int weight, int height, int age, User.Gender gender) {

        String password = UsersManager.getInstance().getLoggedUser().getPassword();
        UsersManager.getInstance().deleteUserRegistration();
        UsersManager.getInstance().registerUser(firstNameStr, lastNameStr, emailStr, password, weight, height, age, gender);
    }

    public void countBMI(User user){
        String pol = user.getStringGender();
        int weight = user.getWeight();
        int height = user.getHeight();
        double heightInMeters = height/10;
        int age = user.getAge();

        double bmi = weight/(heightInMeters*heightInMeters);
        if(bmi<18.5) {
            user.setBmi(User.BMI.SLIM);

        }
        if(bmi>=18.5 && bmi<30){
            user.setBmi(User.BMI.NORMAL);
        }
        if(bmi>=30){
            user.setBmi(User.BMI.FATTENED);
        }

    }

}
