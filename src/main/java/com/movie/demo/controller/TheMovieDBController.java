package com.movie.demo.controller;

import com.movie.demo.service.AllMoviesService;
import com.movie.demo.service.MovieDetailsService;
import com.movie.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
public class TheMovieDBController {
    @Autowired
    private AllMoviesService moviesService;

    @Autowired
    private MovieDetailsService detailsService;

    @PostMapping("/moviesTypes")
    public AllMovies Type(@RequestBody String name) throws Exception {
        return moviesService.movies(name);
    }

    @PostMapping("/movieDetails")
    public MovieDetails details(@RequestBody long id) {
        return detailsService.movieDetails(id);
    }

    @PostMapping("/search/{name}")
    public AllMovies SearchingMovie(@PathVariable String name){
        return moviesService.searchingMovies(name);
    }
}
