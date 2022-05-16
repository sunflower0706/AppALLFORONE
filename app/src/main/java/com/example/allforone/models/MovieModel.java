package com.example.allforone.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    //model class for pủ movies
    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    private String movie_overview;
    private int runtime;


    //constructor
    public MovieModel(String title, String poster_path, String release_date, int movie_id, float vote_average, String movie_overview, int runtime) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
        this.runtime = runtime;

    }

    //getter

    protected MovieModel(Parcel in) {
        movie_id = in.readInt();
        vote_average = in.readFloat();
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_overview = in.readString();
        runtime = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public int getRuntime() {
        return runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
    }
}
