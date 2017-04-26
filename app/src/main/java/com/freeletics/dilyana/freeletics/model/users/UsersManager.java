package com.freeletics.dilyana.freeletics.model.users;

import com.freeletics.dilyana.freeletics.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

    public void registerUser(User u) {

        if(u.getPassword() != null) {
            if (!this.registeredUsers.containsKey(u.getEmail())) {
                this.registeredUsers.put(u.getEmail(), u);
            }
        }
        loggedUser = u;
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

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
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

    public void updateUserInfo(User u) {
        UsersManager.getInstance().deleteUserRegistration();
        UsersManager.getInstance().registerUser(u);
        //todo delete from DB and add to DB
    }
}
