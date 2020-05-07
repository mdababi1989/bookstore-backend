package com.mdababi.bookstore.resource;

import com.mdababi.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginResource {
    @Autowired
    UserService userService;

    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session, HttpServletRequest request){
        System.out.println(request);
        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();
        System.out.println(remoteHost+":"+portNumber);
        return Collections.singletonMap("token", session.getId());

    }

    @RequestMapping("/checkSession")
    public ResponseEntity checkSession(){
        return new ResponseEntity("Session active!", HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public ResponseEntity logout(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout successful!", HttpStatus.OK);

    }
}
