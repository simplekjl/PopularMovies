/*
 * Develop by Jose L Crisostomo S. on 2/3/19 6:53 PM
 * Last modified 2/3/19 6:53 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mainactivity.MainActivity;
import com.example.mainactivity.MovieDetailsActivity;
import com.example.mainactivity.R;
import com.example.mainactivity.network.MoviesDBClient;
import com.example.mainactivity.network.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> mMoviesDataSet;
    private Context mContext;

    public MoviesAdapter(List<Movie> data) {
        mMoviesDataSet = data;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        int itemLayout = R.layout.movie_item;
        boolean hastoBeAttachedRightAway = false;
        View view = LayoutInflater.from(mContext)
                .inflate(itemLayout, viewGroup, hastoBeAttachedRightAway);

        MoviesAdapterViewHolder viewHolder = new MoviesAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder moviesAdapterViewHolder, int i) {

        moviesAdapterViewHolder.setItem(mMoviesDataSet.get(i));
    }

    @Override
    public int getItemCount() {
        return mMoviesDataSet.size();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mReleaseDate;
        private ImageView mImageView;
        private RatingBar mRatingBar;
        private TextView mRating;
        private TextView mSynopsis;
        private MaterialButton mShowMoreButon;


        MoviesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        void setItem(final Movie movie) {
            mTitle.setText(movie.getTitle());
            mReleaseDate.setText(movie.getReleaseDate());
            mRatingBar.setRating(movie.getVotesAvg());
            mRating.setText(String.valueOf(movie.getVotesAvg()));
            StringBuilder sb = new StringBuilder(150);
            if (movie.getPosterPath() != null) {
                sb = new StringBuilder(150);
                sb.append(MoviesDBClient.IMAGE_BASE_URL);
                sb.append(movie.getPosterPath());
            } else {
                sb.append(MoviesDBClient.IMAGE_BASE_URL);
            }

            mShowMoreButon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                    intent.putExtra(MainActivity.MOVIE_OBJECT, movie);
                    mContext.startActivity(intent);
                }
            });

            Picasso.get()
                    .load(sb.toString())
                    .placeholder(R.drawable.thumbnail)
                    .error(R.drawable.thumbnail)
                    .into(mImageView);
            mSynopsis.setText(movie.getOverview());
        }

        private void bindViews(View view) {
            mTitle = view.findViewById(R.id.tv_title);
            mReleaseDate = view.findViewById(R.id.tv_release_date);
            mImageView = view.findViewById(R.id.iv_poster);
            mRatingBar = view.findViewById(R.id.ratingBar);
            mRating = view.findViewById(R.id.tv_rating);
            mSynopsis = view.findViewById(R.id.tv_synopsis);
            mShowMoreButon = view.findViewById(R.id.showMore);
        }
    }
}
