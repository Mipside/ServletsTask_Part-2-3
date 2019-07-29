package com.fariga.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String status;
    private String role;


    public User(String firstName, String lastName, String username, String password, String status, String role) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
        this.status=status;
        this.role=role;
    }
}
