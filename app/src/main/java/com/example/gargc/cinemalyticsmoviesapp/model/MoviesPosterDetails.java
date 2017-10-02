package com.example.gargc.cinemalyticsmoviesapp.model;


import com.google.gson.annotations.SerializedName;

public class MoviesPosterDetails {

    @SerializedName("Id")
    String Id;

    @SerializedName("Title")
    String Title;

    @SerializedName("Description")
    String Description;

    @SerializedName("TrailerLink")
    String TrailerLink;

    @SerializedName("Rating")
    float Rating;

    @SerializedName("Runtime")
    String Runtime;

    @SerializedName("PosterPath")
    String PosterPath;

    public String getReleaseYear() {
        return ReleaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        ReleaseYear = releaseYear;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTrailerLink() {
        return TrailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        TrailerLink = trailerLink;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public float getBudget() {
        return Budget;
    }

    public void setBudget(float budget) {
        Budget = budget;
    }

    public float getRevenue() {
        return Revenue;
    }

    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    @SerializedName("ReleaseYear")
    String ReleaseYear;

    @SerializedName("Genre")
    String Genre;

    @SerializedName("Budget")
    float Budget;

    @SerializedName("Revenue")
    float Revenue;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @SerializedName("Region")
    String region;
}
