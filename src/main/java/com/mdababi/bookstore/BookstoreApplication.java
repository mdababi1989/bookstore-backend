package com.mdababi.bookstore;

import com.mdababi.bookstore.config.FileStorageProperties;
import com.mdababi.bookstore.config.SecurityUtility;
import com.mdababi.bookstore.domain.security.ERole;
import com.mdababi.bookstore.domain.security.Role;
import com.mdababi.bookstore.domain.security.User;
import com.mdababi.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BookstoreApplication implements CommandLineRunner {
    UserService userService;

    @Autowired
    public BookstoreApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args){
        User user1 = new User();
        user1.setFirstName("mohamed");
        user1.setLastName("dababi");
        user1.setUsername("a");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("a"));
        user1.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setName(ERole.ROLE_USER);
        roles.add(role1);
        userService.createUser(user1, roles);
        roles.clear();


    }
}
