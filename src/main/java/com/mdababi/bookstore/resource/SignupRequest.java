package com.mdababi.bookstore.resource;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class SignupRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private Set<String> roles;

}
