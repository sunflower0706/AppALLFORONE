package com.example.allforone;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allforone.Response.MovieSearchResponse;
import com.example.allforone.adapters.MovieRecyclerView;
import com.example.allforone.adapters.OnMovieListener;
import com.example.allforone.models.MovieModel;
import com.example.allforone.request.Servicey;
import com.example.allforone.utils.Credentials;
import com.example.allforone.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity<MovieListViewModel> extends AppCompatActivity implements OnMovieListener {

    //recyclerView

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;


    //ViewModel
    private MovieListViewModel movieListViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //calling the observes
        ObserveAnyChange();

        ConfigureRecyclerView();

        searchMovieApi("fast",1);




        //testing the method
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

            }
        });


    }


    //observing any data change
    private void ObserveAnyChange(){
        MovieListViewModel.getMovie().observe( )
        @Override
        public void onChanged (List<MovieModel> movieModels){
            if (movieModels != null){
                for (MovieModel movieModel : movieModels ) {
                    // get the data in log
                    Log.v("Tag", "onChanged" + movieModel.getTitle());

                    movieRecyclerAdapter.setmMovies(movieModels);
                }
        }
    }


            // calling method in Main Activity
            private void searchMOvie(String query, int pageNUmber){
                movieListViewModel.searchMovieApi(query, pageNUmber);
            }

            private void GetRetrofitResponse() {

                MovieApi movieApi = Servicey.getMovieApi();
                Call<MovieSearchResponse> responseieCall = movieApi
                        .searchMovie(
                                Credentials.API_KEY,
                                "Action",
                                "1"
                        );
                responseieCall.enqueue(new Callback<MovieSearchResponse>() {
                    @Override
                    public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {


                        if (response.code() == 200) {
                            Log.v("Tag", "the response" + response.body().toString());

                            List<MovieModel> movies = new ArrayList<>(response.body().getMovie());

                            for (MovieModel movie : movies) {
                                Log.v("Tag", "The List " + movie.getRelease_date());
                            }
                        } else {
                            try {
                                Log.v("Tag", "Error" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

                    }
                });
            }


            private void GetRetrofitResponseAccordingToID() {
                MovieApi movieApi = Servicey.getMovieApi();
                Call<MovieModel> responseCall =
                        movieApi.getMovie(
                                550,
                                Credentials.API_KEY
                        );
                responseCall.enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.code() == 200) {
                            MovieModel movie = response.body();
                            Log.v("Tag", "The Response" + movie.getTitle());
                        } else {
                            try {
                                Log.v("Tag", "Error" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {

                    }
                });
            }
        };
    }
    //4 calling method in Main activity
    private void  searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query, pageNumber);

    }

    // 5 intializing recyclerView & adding data to it
    private void ConfigureRecyclerView(){
        //live data can't be passed via the constructor
        movieRecyclerAdapter = new MovieRecyclerView( this  );\
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public int onCategoryClick(String category) {
        return 0;
    }
}