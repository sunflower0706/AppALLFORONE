package com.example.allforone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.allforone.MainActivity;
import com.example.allforone.R;
import com.example.allforone.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    public <MovieListViewModel> MovieRecyclerView(MainActivity<MovieListViewModel> movieListViewModelMainActivity) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_items, parent, false);
        return new MovieViewHolder(view, onMovieListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).title.setText(mMovies.get(i).getTitle());
        ((MovieViewHolder)holder).Release_date.setText(mMovies.get(i).getRelease_date());
        ((MovieViewHolder)holder).duration.setText(mMovies.get(i).getRuntime());

        //vote  average is over 10, and our ratingbar is pver 5 stars
        ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(i).getVote_average())/2);

        // imageView: using Glide Library
        Glide.with(holder.itemView.getContext()).load(mMovies.get(i).getPoster_path())
                .into(((MovieViewHolder)holder).imageView);

    }

    @Override
    public int getItemCount() {
        if (mMovies != null){

            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
