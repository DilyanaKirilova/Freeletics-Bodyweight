package com.freeletics.dilyana.freeletics.model.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
}
