package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Crew {
    private String job;
    private String name;
}
