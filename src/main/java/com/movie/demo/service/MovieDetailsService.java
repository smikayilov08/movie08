package com.movie.demo.service;

import com.movie.demo.util.MovieCredit;
import com.movie.demo.util.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {
    @Autowired
    private ApplicationContext context;

    public MovieDetails movieDetails(long id){
        MovieDetails  details = (MovieDetails) context.getBean("movieDetails",id);
        details.setCast(credit(id).getCast());
        MovieCredit credit=credit(id);
        for (int i = 0; i < credit.getCrew().length; i++) {
            if (credit.getCrew()[i].getJob().equals("Director")) {
                details.setDirectorName(credit.getCrew()[i].getName());
                break;
            }
        }
        return details;
    }

    public MovieCredit credit(long id) {
        return (MovieCredit) context.getBean("movieCredit", id);
    }

}
