package com.example.moviesdb.response;

import com.example.moviesdb.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public List<MovieModel> getMovies()
    {
        return movies;
    }

    @Override
    public String toString()
    {
        return "MovieSearchResponse{" +
                "movies"+movies+
                '}';
    }
}
