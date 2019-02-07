/*
 * Develop by Jose L Crisostomo S. on 2/6/19 9:16 PM
 * Last modified 2/6/19 9:16 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mainactivity.network.MoviesDBClient;
import com.example.mainactivity.network.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mReleaseDate;
    private ImageView mImageView;
    private RatingBar mRatingBar;
    private TextView mRating;
    private TextView mSynopsis;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        bindViews();
        if (mMovie != null) {
            setItem(mMovie);
        } else {
            if (getIntent() != null && getIntent().getParcelableExtra(MainActivity.MOVIE_OBJECT) != null) {
                mMovie = getIntent().getParcelableExtra(MainActivity.MOVIE_OBJECT);
                setItem(mMovie);
            }
        }
    }

    void setItem(final Movie movie) {
        setTitle(movie.getTitle());
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


        Picasso.get()
                .load(sb.toString())
                .placeholder(R.drawable.thumbnail)
                .error(R.drawable.thumbnail)
                .into(mImageView);
        mSynopsis.setText(movie.getOverview());
    }

    private void bindViews() {
        mTitle = findViewById(R.id.tv_title);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mImageView = findViewById(R.id.iv_poster);
        mRatingBar = findViewById(R.id.ratingBar);
        mRating = findViewById(R.id.tv_rating);
        mSynopsis = findViewById(R.id.tv_synopsis);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mMovie = savedInstanceState.getParcelable(MainActivity.MOVIE_OBJECT);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMovie != null) {
            outState.putParcelable(MainActivity.MOVIE_OBJECT, mMovie);
        }
        super.onSaveInstanceState(outState);

    }
}
