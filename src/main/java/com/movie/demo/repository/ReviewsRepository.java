package com.movie.demo.repository;

import com.movie.demo.data.DtoReviews;
import com.movie.demo.data.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    @Query(value = "select ud.user_name,r.movies_name,r.user_comments   from reviews r \n" +
            "inner join user_details ud on r.users_id =ud.id and r.movies_id=:id", nativeQuery = true)
    List<DtoReviews> findAllComments(@Param("id") int id);

    @Query(value="select r.id from reviews r" +
            " where r.movies_name=:movies_name and r.users_id=:users_id and r.user_comments=:user_comments and r.movies_id=:movie_id",nativeQuery=true)
    int deleteReview(@Param("movies_name")String movieName,
                     @Param("users_id")int id,
                     @Param("user_comments")String comment,
                     @Param("movie_id") int movieId);
}
