package com.mdababi.bookstore;

import com.mdababi.bookstore.config.SecurityUtility;
import com.mdababi.bookstore.domain.User;
import com.mdababi.bookstore.domain.security.Role;
import com.mdababi.bookstore.domain.security.UserRole;
import com.mdababi.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("mohamed");
        user1.setEmail("mdababi@gmail.com");
        user1.setLastName("dababi");
        user1.setUsername("mdababi1989");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("azerty"));
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        role1.setRoleId(1);
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);
        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("wael");
        user2.setEmail("wael@gmail.com");
        user2.setLastName("ayadi");
        user2.setUsername("wael");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("azerty"));
        Role role2 = new Role();
        role2.setName("ROLE_USER");
        role2.setRoleId(1);
        userRoles.add(new UserRole(user2, role2));
        userService.createUser(user2, userRoles);
        userRoles.clear();

    }
}
