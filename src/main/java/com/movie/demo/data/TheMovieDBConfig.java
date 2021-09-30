package com.movie.demo.data;

import com.movie.demo.util.Genres;
import com.movie.demo.util.MovieCredit;
import com.movie.demo.util.MovieDetails;
import com.movie.demo.util.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TheMovieDBConfig {

    @Autowired
    private RestTemplate template;

    @Value("${apiKey}")
    private  String API_KEY;

    @Bean(name = "movieType")
    @Scope("prototype")
    public MovieType getMovieType(String name) {
        MovieType quote = template.getForObject(
                "https://api.themoviedb.org/3/movie/" + name + "?api_key="+API_KEY, MovieType.class
        );
        return quote;
    }

    @Bean(name = "genre")
    @Scope("prototype")
    public Genres getGenres(long id) {
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key="+API_KEY, Genres.class);
    }

    @Bean(name="movieDetails")
    @Scope("prototype")
    public MovieDetails getMovieDetails(long id){
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key="+API_KEY, MovieDetails.class);
    }

    @Bean(name = "movieCredit")
    @Scope("prototype")
    public MovieCredit getMovieCredit(long id){
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "/credits?api_key="+API_KEY, MovieCredit.class);
    }

    @Bean(name="searchMovie")
    @Scope("prototype")
    public MovieType getSearchingMovie(String name){
       MovieType type=template.getForObject(
                "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&query="+name,MovieType.class
        );
        return type;
    }
}
