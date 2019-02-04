/*
 * Develop by Jose L Crisostomo S. on 2/3/19 5:29 PM
 * Last modified 2/3/19 5:29 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity.network;

import com.example.mainactivity.network.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDBService {
//https://api.themoviedb.org/3/movie/
    @GET("top_rated")
    Call<MoviesResponse> getHihestRatedMovies(@Query("api_key") String apiKey);

    @GET("popular")
    Call<MoviesResponse> getMostPopularMovies(@Query("api_key") String apiKey);
}
