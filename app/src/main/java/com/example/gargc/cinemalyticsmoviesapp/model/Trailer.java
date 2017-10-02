package com.example.gargc.cinemalyticsmoviesapp.model;


import com.google.gson.annotations.SerializedName;

public class Trailer {
    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getTrailerLink() {
        return TrailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        TrailerLink = trailerLink;
    }

    @SerializedName("PosterPath")
    String PosterPath;

    @SerializedName("TrailerLink")
    String TrailerLink;
}
