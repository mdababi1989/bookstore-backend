package com.mdababi.bookstore.resource;

import com.mdababi.bookstore.domain.security.ERole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String jwt;
    private Long id;
    private String username;
    private List<String> roles;

    public JwtResponse(String jwt, Long id, String username, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
