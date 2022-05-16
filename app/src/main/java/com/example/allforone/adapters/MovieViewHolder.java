package com.example.allforone.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allforone.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Widgets
    TextView title, Release_date, duration;
    ImageView imageView;
    RatingBar ratingBar;
    //listenner
    onMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        title = itemView.findViewById(R.id.movie_title);
        Release_date = itemView.findViewById(R.id.movie_category);
        duration = itemView.findViewById(R.id.movie_duration);

        imageView = itemView.findViewById(R.id.movie_img);

        ratingBar = itemView.findViewById(R.id.rating_bar);

        itemView.setOnClickListener(this );



    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition())
    }
}
