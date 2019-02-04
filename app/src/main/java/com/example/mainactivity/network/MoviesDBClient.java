/*
 * Develop by Jose L Crisostomo S. on 2/3/19 6:00 PM
 * Last modified 2/3/19 6:00 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesDBClient {

    public static Retrofit sRetrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static synchronized Retrofit getInstance(){
        if(sRetrofit ==null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

}
