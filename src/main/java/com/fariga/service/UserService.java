package com.fariga.service;

import com.fariga.entity.User;
import com.fariga.data.Storage;

import java.util.List;

public class UserService {
    private Storage storage;

    public UserService(Storage storage) {
        this.storage = storage;
    }

    public List<User> getListOfUsers() {
        return storage.listOfUsers();
    }

    public boolean check(String username, String password) {
        for (User u : getListOfUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkingUsernameInList(String username) {
        for (User u : getListOfUsers()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public boolean updateLoginStatus(String username) {
        for (User u : getListOfUsers()) {
            if (u.getUsername().equals(username)) {
                u.setStatus("logged-in");
                return true;
            }
        }
        return false;
    }

    public boolean logout(String username) {
        for (User u : storage.listOfUsers())
            if (u.getUsername().equals(username)) {
                u.setStatus("not_logged-in");
                return true;
            }
        return false;
    }
}
