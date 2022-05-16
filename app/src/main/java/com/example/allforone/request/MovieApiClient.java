package com.example.allforone.request;

import android.graphics.Movie;
import android.telecom.Call;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.allforone.AppExcutors;
import com.example.allforone.Response.MovieSearchResponse;
import com.example.allforone.models.MovieModel;
import com.example.allforone.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class MovieApiClient {
    //Live data

    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    //making Global RUNNABLE
    private RetrieveMovieRunnable retrieveMovieRunnable;






    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();

    }
    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }

    public void searchMovieApi(String query, int pageNumber){

        if (retrieveMovieRunnable != null){
            retrieveMovieRunnable = null;
        }

        retrieveMovieRunnable = new RetrieveMovieRunnable(query, pageNumber)

        final Future myHandler;


        myHandler = AppExcutors.getInstance.netWorkIO.submit(retrieveMovieRunnable);


        AppExcutors.getInstance.netWorkIO().schedule(new Runnable(){
            @Override
            public void run(){
                // cancelling  the retrofit call
               myHandler .cancel(true);
            },
        },delay:5000, TimeUnit.MICROSECONDS);
        }
    }
    // Retreiving data from  RestApi by runable class
    //  Retreiving data from  RestApi by runable class
//  we have 2 types of queries: The ID
    private class RetrieveMovieRunnable implements Runnable{
        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequestm = false;
        }

        @Override
        public void run() {
            // getting the response object
            try {
                Response response = getMovies(query, pageNumber).execute();


                if (cancelRequest){
                    return;
                }

                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovie());
                    if (pageNumber == 1) {
                        // sending data to live
                        mMovie.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mMovie.getValue();
                        currentMovies.addAll(list);
                        mMovie.postvalue(currentMovies);
                    }
                }
                else{
                    String  error = response.errorBody().string();
                    Log.v("Tag", "Erorr"+ error);
                    mMovie.postValue(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
                return Servicey.getMovieApi().searchMovie(
                        Credentials.API_KEY,
                        query,
                        String.valueOf(pageNumber)
                )
        }


    }
    private void cancelRequest(){
        Log.v("Tag","Cancelling Search Request");
        cancelRequest = true;
    }
}
