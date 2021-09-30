package com.movie.demo.service;

import com.movie.demo.data.WatchList;
import com.movie.demo.repository.WatchListRepository;
import com.movie.demo.util.CurrentUser;
import com.movie.demo.util.MovieDetails;
import com.movie.demo.util.SearchWatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private MovieDetailsService detailsService;

    @Autowired
    private SearchWatchList searchWatchList;

    public void addToList(String movieName,long movieId) {
        WatchList list = new WatchList(movieName, currentUser.getUser(),movieId);
        watchListRepository.save(list);
    }

    public List<Long> moviesId(){
        return watchListRepository.findMoviesId(currentUser.getUser().getId());
    }

    public List<MovieDetails> getWatchList(){
        List<MovieDetails> moviesList=new ArrayList<>();
        for (Long movieId:moviesId()){
            moviesList.add(detailsService.movieDetails(movieId));
        }
        return moviesList;
    }

    public void deleteMovies(long movieId) {
        watchListRepository.delete(movieId, currentUser.getUser().getId());
    }

    public SearchWatchList searchMovie(long id) {
        if(null!=watchListRepository.search(currentUser.getUser().getId(),id)) {
            searchWatchList.setIdPresent(true);
            return searchWatchList;
        }
        else{
            searchWatchList.setIdPresent(false);
            return searchWatchList;
        }
    }
}
