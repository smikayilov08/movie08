package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class MovieType {

    private Results[] results;
    private String[] photos;
    private long[] id;
}
