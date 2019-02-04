/*
 * Develop by Jose L Crisostomo S. on 2/3/19 5:44 PM
 * Last modified 2/3/19 5:44 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("overview")
    String overview;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("vote_average")
    int votesAvg;
    @SerializedName("popularity")
    int popularity;
    @SerializedName("release_date")
    String releaseDate;

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        votesAvg = in.readInt();
        popularity = in.readInt();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeInt(votesAvg);
        dest.writeInt(popularity);
        dest.writeString(releaseDate);
    }
}
