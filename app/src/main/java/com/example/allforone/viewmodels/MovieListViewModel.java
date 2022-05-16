package com.example.allforone.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.allforone.models.MovieModel;
import com.example.allforone.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel{

    //this class is used fo VIEWMODELS

    private MovieRepository movieRepository;

    //constructor


    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

}
