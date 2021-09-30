package com.movie.demo.repository;

import com.movie.demo.data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users,Integer> {
    Optional<Users> findByName(String name);
}
