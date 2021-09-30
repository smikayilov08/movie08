package com.movie.demo.controller;

import com.movie.demo.service.WatchListService;
import com.movie.demo.util.MovieDetails;
import com.movie.demo.util.SearchWatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
public class WatchListController {
    @Autowired
    private WatchListService service;

    @PostMapping("/watchlist/{movieName}/{movieId}")
    public void addToList(@PathVariable String movieName,@PathVariable long movieId) {
        service.addToList(movieName,movieId);
    }

    @GetMapping("/watchlist")
    public List<MovieDetails>movies2(){
        return service.getWatchList();
    }

    @DeleteMapping("/watchlist/{movieId}")
    public void deleteMovie(@PathVariable long movieId){
        service.deleteMovies(movieId);
    }

    @GetMapping("/watchlist/search/{movieId}")
    public SearchWatchList searchFromWatchList(@PathVariable long movieId){
        return  service.searchMovie(movieId);
    }
}
