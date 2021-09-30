package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails {
    private long id;
    private float budget;
    private String backdrop_path;
    private String original_title;
    private String overview;
    private String poster_path;
    private short runtime;
    private float vote_average;
    private long revenue;
    private Value[] genres;
    private Cast[] cast;
    private String directorName;
}
