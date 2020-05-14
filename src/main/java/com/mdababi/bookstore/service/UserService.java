package com.mdababi.bookstore.service;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles);
}
