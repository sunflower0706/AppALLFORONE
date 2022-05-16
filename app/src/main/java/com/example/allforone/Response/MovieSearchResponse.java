package com.example.allforone.Response;

import androidx.lifecycle.LiveData;

import com.example.allforone.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

// this class is for getting multiple movie (movie list) -popular movies
public class MovieSearchResponse {
    @SerializedName("total results")
    @Expose
    private int total_count;


    @SerializedName("results")
    @Expose()
    private List<MovieModel> movie;

    public int getTotal_count(){
        return total_count;
    }
    public List<MovieModel> getMovie(){
        List<MovieModel> movies = new ArrayList<>();
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movie=" + movie +
                '}';
    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }
    // calling the method
    public void searchMovieApi(String query, int pageNumeber){
        movieApiClient.searchMovieApi(query,pageNumeber);
    }
}
