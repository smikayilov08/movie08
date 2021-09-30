package com.movie.demo.controller;

import com.movie.demo.security.JwtUtil;
import com.movie.demo.security.data.AuthenticationRequest;
import com.movie.demo.security.data.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins="*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
public class LoginController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService service;
    @Autowired
    private JwtUtil util;

    @PostMapping(value = "/login" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> entity(@RequestBody AuthenticationRequest request){
        try{
            manager.authenticate(
                  new  UsernamePasswordAuthenticationToken(request.getName(),request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
        final UserDetails user=service.loadUserByUsername(request.getName());
        final  String jwt=util.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
