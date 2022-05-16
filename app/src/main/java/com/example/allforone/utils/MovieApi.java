package com.example.allforone.utils;

import com.example.allforone.Response.MovieSearchResponse;
import com.example.allforone.models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //search for movie
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page

    );
    //search with id
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
        @Path("movie_id") int movie_id,
        @Query("api_key") String api_key

    );



}
