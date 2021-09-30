package com.movie.demo.util;

import com.movie.demo.data.Users;
import com.movie.demo.repository.UsersRepository;
import com.movie.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CurrentUser {
    @Autowired
    private UsersRepository usersRepository;

    public Users getUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails=(MyUserDetails)authentication.getPrincipal();
        Optional<Users>users=usersRepository.findByName(userDetails.getUsername());
        users.orElseThrow(()->new UsernameNotFoundException("User not found!"));
        return users.get();
    }
}
