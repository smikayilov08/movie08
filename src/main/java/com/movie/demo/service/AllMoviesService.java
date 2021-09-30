package com.movie.demo.service;

import com.movie.demo.util.AllMovies;
import com.movie.demo.util.Genres;
import com.movie.demo.util.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AllMoviesService {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private AllMovies movies;

    public AllMovies movies(String name){
        name = name.substring(1, name.length() - 1);
        MovieType movieType = (MovieType) context.getBean("movieType", name);

        String photosArray[] = new String[movieType.getResults().length];

        long idArray[] = new long[movieType.getResults().length];

        Genres[] genres = new Genres[movieType.getResults().length];

        for (int i = 0; i < photosArray.length; i++) {
            photosArray[i] = movieType.getResults()[i].getPoster_path();
            idArray[i] = movieType.getResults()[i].getId();
            genres[i] = getGenres(movieType.getResults()[i].getId());
        }
        movieType.setPhotos(photosArray);
        movieType.setId(idArray);
        movies.setGenres(genres);
        movies.setMovieType(movieType);
        return movies;
    }

    public Genres getGenres(long id) {
        return (Genres) context.getBean("genre", id);
    }

    public AllMovies searchingMovies(String name){
//        name = name.substring(1, name.length() - 1);
        MovieType movieType = (MovieType) context.getBean("searchMovie", name);

        String photosArray[] = new String[movieType.getResults().length];

        long idArray[] = new long[movieType.getResults().length];

        Genres[] genres = new Genres[movieType.getResults().length];

        for (int i = 0; i < photosArray.length; i++) {
            photosArray[i] = movieType.getResults()[i].getPoster_path();
            idArray[i] = movieType.getResults()[i].getId();
            genres[i] = getGenres(movieType.getResults()[i].getId());
        }
        movieType.setPhotos(photosArray);
        movieType.setId(idArray);
        movies.setGenres(genres);
        movies.setMovieType(movieType);
        return movies;
    }
}
