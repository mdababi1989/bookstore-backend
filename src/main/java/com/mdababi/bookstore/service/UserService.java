package com.mdababi.bookstore.service;

import com.mdababi.bookstore.domain.security.Role;
import com.mdababi.bookstore.domain.security.User;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<Role> roles);
}
