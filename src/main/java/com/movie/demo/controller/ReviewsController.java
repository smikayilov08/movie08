package com.movie.demo.controller;

import com.movie.demo.data.Comment;
import com.movie.demo.data.DtoReviews;
import com.movie.demo.data.Reviews;
import com.movie.demo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
public class ReviewsController {
    @Autowired
    private ReviewsService service;

    @PostMapping("/movies/{movieName}/{id}/comments")
    public void addComments(@PathVariable String movieName, @RequestBody Comment comments,@PathVariable int id) {
        service.addComments(comments, movieName,id);
    }

    @GetMapping("/movies/{id}/comments")
    public List<DtoReviews> comments(@PathVariable int id) {
        return service.comments(id);
    }

    @DeleteMapping("/movies/{movieName}/{id}/comments")
    public void deleteComments(@RequestBody Reviews reviews,@PathVariable int id) throws Exception {
        service.deleteComments(reviews,id);
    }
}
