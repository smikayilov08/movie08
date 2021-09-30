package com.movie.demo.controller;

import com.movie.demo.data.UserProfile;
import com.movie.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
public class SignUpController {

    @Autowired
    private UserProfileService service;

    @PostMapping("/signup")
    public void signupUsers(@RequestBody UserProfile profile){
        service.addUser(profile);
    }
}
