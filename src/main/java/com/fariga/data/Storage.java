package com.fariga.data;

import com.fariga.entity.User;

import java.util.LinkedList;
import java.util.List;

public class Storage {

    private List<User> users = new LinkedList<>();

    public Storage() {
        initialList();
    }

    private void initialList() {
        createBasicListOfUsers("pavel", "ivanov", "firstUsername", "111", "not_logged-in", "User");
        createBasicListOfUsers("pavel2", "ivanov2", "firstUsername2", "1112", "not_logged-in", "Admin");
    }

    private void createBasicListOfUsers(String fname, String lname, String uname, String pass, String stat, String role) {
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setUsername(uname);
        user.setPassword(pass);
        user.setStatus(stat);
        user.setRole(role);
        users.add(user);
    }

    public List<User> listOfUsers() {
        return new LinkedList<>(users);
    }

    public User getUserInfo(String nickname) {
        for (User u : users) {
            if (u.getUsername().equals(nickname)) {
                return u;
            }
        }
        User nonUser = new User();
        nonUser.setUsername("nonUser");
        return nonUser;
    }

    public List<User> createUser(User user) {
        users.add(user);
        return listOfUsers();
    }

    public List<User> updateUserInfo(String fname, String lname, String uname) {
        for (User u : users) {
            if (u.getUsername().equals(uname)) {
                u.setFirstName(fname);
                u.setLastName(lname);
                u.setUsername(uname);
                return listOfUsers();
            }
        }
        return listOfUsers();
    }

    public List<User> deleteUser(String uname) {
        for (User u : users) {
            if (u.getUsername().equals(uname)) {
                users.remove(u);
                return listOfUsers();
            }
        }
        return listOfUsers();
    }

}
