package com.example.allforone.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.allforone.models.MovieModel;
import com.example.allforone.request.MovieApiClient;
import com.example.allforone.utils.MovieApi;

import java.util.List;

public class MovieRepository {
    // this class is acting as repositories


    private static MovieRepository instance;

    private MovieApiClient movieApiClient

    public static MovieRepository getInstance(){

        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;


    }
    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();}

}
