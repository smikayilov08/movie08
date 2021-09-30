package com.movie.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@ToString
public class Genres {
    private Value[] genres;
}
