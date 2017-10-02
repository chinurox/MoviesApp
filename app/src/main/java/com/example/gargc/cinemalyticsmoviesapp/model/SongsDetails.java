package com.example.gargc.cinemalyticsmoviesapp.model;


import com.google.gson.annotations.SerializedName;

public class SongsDetails {

    @SerializedName("Id")
    String id;

    @SerializedName("Title")
    String title;

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getYoutubeReleaseDate() {
        return youtubeReleaseDate;
    }

    public void setYoutubeReleaseDate(String youtubeReleaseDate) {
        this.youtubeReleaseDate = youtubeReleaseDate;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("Duration")
    Float duration;

    @SerializedName("YouTubeVideoId")
    String youTubeId;

    @SerializedName("YouTubeReleaseDate")
    String youtubeReleaseDate;

    @SerializedName("YouTubeLink")
    String youtubeLink;
}
