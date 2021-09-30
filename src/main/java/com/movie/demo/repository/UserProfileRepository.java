package com.movie.demo.repository;

import com.movie.demo.data.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile,Integer> {
}
