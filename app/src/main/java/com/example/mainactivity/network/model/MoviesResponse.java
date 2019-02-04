/*
 * Develop by Jose L Crisostomo S. on 2/3/19 5:40 PM
 * Last modified 2/3/19 5:40 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse implements Parcelable {

    @SerializedName("page")
    String page;
    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;
    @SerializedName("results")
    List<Movie> moviesList;

    protected MoviesResponse(Parcel in) {
        page = in.readString();
        totalResults = in.readInt();
        totalPages = in.readInt();
        moviesList = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel in) {
            return new MoviesResponse(in);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(page);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
        dest.writeTypedList(moviesList);
    }
}
