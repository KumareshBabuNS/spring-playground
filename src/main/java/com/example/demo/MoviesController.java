package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MoviesController {

    @Autowired
    private OMDBService omdbService;

    @GetMapping("")
    public List<Movie> searchMovies(@RequestParam String q) {
        return omdbService.getMovies(q);
    }

}
