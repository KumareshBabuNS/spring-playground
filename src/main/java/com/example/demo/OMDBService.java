package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@PropertySource("classpath:omdb.properties")
public class OMDBService {

    @Value("${api.key}")
    private String apikey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> getMovies(String q) {
        OMDBResponse response = this.restTemplate.getForObject(
                "http://www.omdbapi.com/?s={q}&apikey={apikey}",
                OMDBResponse.class,
                q,
                apikey
        );

        return response.getSearch();
    }

}