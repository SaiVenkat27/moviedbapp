package com.example.moviesdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesdb.models.MovieModel;
import com.example.moviesdb.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {


    private MovieRepository movieRepository;
    public MovieListViewModel()
    {
        movieRepository = MovieRepository.getInstance();
    }
    public LiveData<List<MovieModel>> getMovies()
    {
        return movieRepository.getMovies();
    }


    public void searchMovieApi(String query)
    {
        movieRepository.searchMovieApi(query);
    }
}
