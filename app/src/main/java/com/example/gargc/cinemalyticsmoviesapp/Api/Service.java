package com.example.gargc.cinemalyticsmoviesapp.Api;


import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;
import com.example.gargc.cinemalyticsmoviesapp.model.SongsDetails;
import com.example.gargc.cinemalyticsmoviesapp.model.Trailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/latest-trailers/")
    Call<List<Trailer>> getPopularMovies(@Query("auth_token") String apiKey);

    @GET("analytics/TopMovies/")
    Call<List<MoviesPosterDetails>> getTopMovies(@Query("auth_token") String apiKey);

    @GET("analytics/TopGrossedMovies/")
    Call<List<MoviesPosterDetails>> getTopGrossedMovies(@Query("auth_token") String apiKey);

    @GET("movie/upcoming/")
    Call<List<MoviesPosterDetails>> getUpcomingMovies(@Query("auth_token") String apiKey);

    @GET("analytics/FemaleActorsByHighestRating/")
    Call<List<ActorActresses>> getActresses(@Query("auth_token") String apiKey);

    @GET("analytics/MaleActorsByHighestRating/")
    Call<List<ActorActresses>> getActors(@Query("auth_token") String apiKey);

    @GET("movie/{movie_id}/actors/")
    Call<List<ActorActresses>> getCast(@Path("movie_id") String id, @Query("auth_token") String apiKey);

    @GET("movie/{movie_id}/songs/")
    Call<List<SongsDetails>> getSongs(@Path("movie_id") String id, @Query("auth_token") String apiKey);

    @GET("actor/{movie_id}/movies/")
    Call<List<MoviesPosterDetails>> getMoviesOfActor(@Path("movie_id") String id,@Query("limit") int number,@Query("auth_token") String apiKey);

    @GET("movie/title/")
    Call<List<MoviesPosterDetails>> getSearchedMovied(@Query("value") String name,@Query("auth_token") String apiKey);




}
