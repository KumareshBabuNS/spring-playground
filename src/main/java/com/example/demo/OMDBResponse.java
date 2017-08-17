package com.example.demo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class OMDBResponse {

    private List<Movie> search;
    private int totalResults;
    private boolean response;

    @JsonGetter("search")
    public List<Movie> getSearch() {
        return search;
    }

    @JsonSetter("Search")
    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    @JsonGetter("totalResults")
    public int getTotalResults() {
        return totalResults;
    }

    @JsonSetter("totalResults")
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @JsonGetter("response")
    public boolean isResponse() {
        return response;
    }

    @JsonSetter("Response")
    public void setResponse(boolean response) {
        this.response = response;
    }
}
