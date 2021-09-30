package com.movie.demo.service;

import com.movie.demo.data.UserProfile;
import com.movie.demo.data.Users;
import com.movie.demo.repository.UserProfileRepository;
import com.movie.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void addUser(UserProfile profile){
        profile.setPassword(encoder.encode(profile.getPassword()));
        repository.save(profile);
        addUserToUsers(profile);
    }

    private void addUserToUsers(UserProfile profile){
        Users users=new Users(profile.getId(),profile.getUserName(),profile.getPassword(),"ROLE_USER",profile);
        usersRepository.save(users);
    }
}
