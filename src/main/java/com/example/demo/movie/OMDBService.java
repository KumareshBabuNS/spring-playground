package com.example.demo.movie;

import com.example.demo.movie.Movie;
import com.example.demo.movie.OMDBPropertiesConfig;
import com.example.demo.movie.OMDBResponse;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@PropertySource("classpath:omdb.properties")
public class OMDBService {

    private final OMDBPropertiesConfig config;
    private final RestTemplate restTemplate = new RestTemplate();

    public OMDBService(OMDBPropertiesConfig config) {
        this.config = config;
    }

    public List<Movie> getMovies(String q) {
        OMDBResponse response = this.restTemplate.getForObject(
                "http://www.omdbapi.com/?s={q}&apikey={apikey}",
                OMDBResponse.class,
                q,
                config.getKey()
        );

        return response.getSearch();
    }

    protected RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

}