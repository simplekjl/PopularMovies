/*
 * Develop by Jose L Crisostomo S. on 2/3/19 4:08 PM
 * Last modified 2/3/19 4:08 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mainactivity.adapters.MoviesAdapter;
import com.example.mainactivity.network.MoviesDBClient;
import com.example.mainactivity.network.MoviesDBService;
import com.example.mainactivity.network.model.MoviesResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private TextView mErrorMessage;
    private static String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        getMostPopularMovies();
    }

    private void bindViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mErrorMessage = (TextView) findViewById(R.id.error_message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_most_popular:
                getMostPopularMovies();
                return true;
            case R.id.action_highest_rated:
                getTopRatedMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getTopRatedMovies() {
        MoviesDBService service = MoviesDBClient.getInstance().create(MoviesDBService.class);
        Call<MoviesResponse> result = service.getHihestRatedMovies(getString(R.string.api_key));
        showloader();
        result.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MoviesResponse moviesResponse = response.body();
                        if (moviesResponse.getMoviesList() != null) {
                            mMoviesAdapter = new MoviesAdapter(moviesResponse.getMoviesList());
                            showResults();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                showErrorMessage();
                Log.e(TAG, "onFailure: "+ t.getMessage() );
            }
        });
    }

    private void getMostPopularMovies() {
        MoviesDBService service = MoviesDBClient.getInstance().create(MoviesDBService.class);
        Call<MoviesResponse> result = service.getMostPopularMovies(getString(R.string.api_key));
        showloader();
        result.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                if (response.body() != null) {
                    MoviesResponse moviesResponse = response.body();
                    if (moviesResponse.getMoviesList() != null) {
                        mMoviesAdapter = new MoviesAdapter(moviesResponse.getMoviesList());
                        showResults();
                    }}
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                showErrorMessage();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    void showloader() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    //region ShowResults
    void showResults() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
        //setup recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMoviesAdapter);

    }
    //endRegion showresults

    //region Error Message
    void showErrorMessage() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }
    //endRegion error message
}
