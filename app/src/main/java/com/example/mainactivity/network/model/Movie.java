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
    float votesAvg;
    @SerializedName("popularity")
    float popularity;
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
        dest.writeFloat(votesAvg);
        dest.writeFloat(popularity);
        dest.writeString(releaseDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public float getVotesAvg() {
        return votesAvg;
    }

    public void setVotesAvg(int votesAvg) {
        this.votesAvg = votesAvg;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
