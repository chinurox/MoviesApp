package com.example.gargc.cinemalyticsmoviesapp.model;


import com.google.gson.annotations.SerializedName;

public class ActorActresses {

    @SerializedName("Id")
    String id;
    @SerializedName("Name")
    String name;
    @SerializedName("Gender")
    String gender;
    @SerializedName("ProfilePath")
    String profilepath;
    @SerializedName("Rating")
    Float rating;

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepath() {
        return profilepath;
    }

    public void setProfilepath(String profilepath) {
        this.profilepath = profilepath;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getTwitterpage() {
        return twitterpage;
    }

    public void setTwitterpage(String twitterpage) {
        this.twitterpage = twitterpage;
    }

    public String getFacebookpage() {
        return facebookpage;
    }

    public void setFacebookpage(String facebookpage) {
        this.facebookpage = facebookpage;
    }

    @SerializedName("DateOfBirth")

    String dateofbirth;
    @SerializedName("BirthPlace")
    String birthplace;
    @SerializedName("OfficialFacebookPage")
    String facebookpage;
    @SerializedName("OfficialTwitterPage")
    String twitterpage;

}
