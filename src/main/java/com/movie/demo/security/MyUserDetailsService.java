package com.movie.demo.security;

import com.movie.demo.data.Users;
import com.movie.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Users> user = repository.findByName(name);
        user.orElseThrow( ()-> new UsernameNotFoundException("User Not Found"));
        return user.map(MyUserDetails::new).get();
    }
}
