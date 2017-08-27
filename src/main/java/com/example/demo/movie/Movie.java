package com.example.demo.movie;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Movie {

    private String title;
    private int year;
    private String imdbID;
    private String type;
    private String poster;

    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("year")
    public int getYear() {
        return year;
    }

    @JsonSetter("Year")
    public void setYear(int year) {
        this.year = year;
    }

    @JsonGetter("imdbId")
    public String getImdbID() {
        return imdbID;
    }

    @JsonSetter("imdbID")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

    @JsonSetter("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter("poster")
    public String getPoster() {
        return poster;
    }

    @JsonSetter("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }
}
