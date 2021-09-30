package com.movie.demo.service;

import com.movie.demo.data.Comment;
import com.movie.demo.data.DtoReviews;
import com.movie.demo.data.Reviews;
import com.movie.demo.repository.ReviewsRepository;
import com.movie.demo.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private CurrentUser currentUser;

    public void addComments(Comment comments, String movieNames,int id) {
        Reviews reviews = new Reviews(comments, movieNames, currentUser.getUser(),id);
        reviewsRepository.save(reviews);
    }

    public List<DtoReviews> comments(int movieId) {
        return reviewsRepository.findAllComments(movieId);
    }

   public void deleteComments(Reviews reviews,int movieId){
        int id= reviewsRepository.deleteReview(reviews.getMoviesName(),
                currentUser.getUser().getId(),reviews.getComments().getUserComments(),movieId);
        reviewsRepository.deleteById(id);
   }

}
