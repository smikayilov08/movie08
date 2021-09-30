package com.movie.demo.repository;

import com.movie.demo.data.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface WatchListRepository extends JpaRepository<WatchList,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from watch_list wl   where wl.movies_id =:movie_id and wl.users_id =:user_id", nativeQuery = true)
    void delete(@Param("movie_id") long movieId, @Param("user_id") int id);

    @Query(value="select movies_id from watch_list where users_id=:id",nativeQuery=true)
    List<Long>findMoviesId(@Param("id") int id);

    @Query(value="select users_id from watch_list where users_id=:user_id and movies_id=:movie_id",nativeQuery=true)
    Integer search(@Param("user_id") int user_id,@Param("movie_id") long movie_id);
}
