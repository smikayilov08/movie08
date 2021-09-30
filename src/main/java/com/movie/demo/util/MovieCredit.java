package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieCredit {
    private Cast[] cast;
    private Crew[]crew;
}
