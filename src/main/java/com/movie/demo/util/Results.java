package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class Results {
    private String original_title;
    private String poster_path;
    private float vote_average;
    private long id;
}
