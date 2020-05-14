package com.mdababi.bookstore.service.impl;

import com.mdababi.bookstore.domain.security.Role;
import com.mdababi.bookstore.domain.security.User;
import com.mdababi.bookstore.repository.RoleRepository;
import com.mdababi.bookstore.repository.UserRepository;
import com.mdababi.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public User createUser(User user, Set<Role> roles) {
        User databaseUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (databaseUser != null) {
            LOG.info("user {} exist! nothing will be done");
            return null;
        } else {
            for (Role role : roles) {
                if (roleRepository.findByName(role.getName()) == null) {
                    roleRepository.save(role);
                }
                user.getRoles().add(role);
            }
            return userRepository.save(user);
        }
    }
}
