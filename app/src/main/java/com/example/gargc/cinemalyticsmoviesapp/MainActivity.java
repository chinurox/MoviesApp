package com.example.gargc.cinemalyticsmoviesapp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gargc.cinemalyticsmoviesapp.Adapter.ActorsActressAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.ActressesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.ToolbarAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.TopMoviesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Api.Client;
import com.example.gargc.cinemalyticsmoviesapp.Api.Service;
import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;
import com.example.gargc.cinemalyticsmoviesapp.model.Trailer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ToolbarAdapter adapter;
    TopMoviesAdapter topMoviesAdapter,topGrossedMoviesAdapter,upcomingMoviesAdapter;
    ActorsActressAdapter popularActors;
    ActressesAdapter popularActresses;


     List<Trailer> trailerList;
     List<MoviesPosterDetails> moviesPosterDetailsList;
     List<MoviesPosterDetails> topGrossedMoviesList;
     List<MoviesPosterDetails> upComingMoviesList;
     List<ActorActresses> actressesList;
     List<ActorActresses> actorsList;

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView topGrossedMoviesRecyclerView;
    RecyclerView upComingMoviesRecyclerView;
    RecyclerView popularActressRecyclerView;
    RecyclerView popularActorsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        initViews();



    }

    private void initViews(){
        trailerList = new ArrayList<>();
        moviesPosterDetailsList=new ArrayList<>();
        topGrossedMoviesList=new ArrayList<>();
        upComingMoviesList=new ArrayList<>();
        actressesList=new ArrayList<>();
        actorsList=new ArrayList<>();


        adapter = new ToolbarAdapter(this, trailerList);
        topMoviesAdapter=new TopMoviesAdapter(this,moviesPosterDetailsList);
        topGrossedMoviesAdapter=new TopMoviesAdapter(this,topGrossedMoviesList);
        upcomingMoviesAdapter=new TopMoviesAdapter(this,upComingMoviesList);
        popularActresses=new ActressesAdapter(this,actressesList);
        popularActors=new ActorsActressAdapter(this,actorsList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView1=(RecyclerView) findViewById(R.id.topmoviesinrecyclerview);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setAdapter(topMoviesAdapter);
        topMoviesAdapter.notifyDataSetChanged();


        topGrossedMoviesRecyclerView=(RecyclerView) findViewById(R.id.topgrossedmoviesinrecyclerview);
        RecyclerView.LayoutManager mLayoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        topGrossedMoviesRecyclerView.setLayoutManager(mLayoutManager2);
        topGrossedMoviesRecyclerView.setAdapter(topGrossedMoviesAdapter);
        topGrossedMoviesAdapter.notifyDataSetChanged();

        upComingMoviesRecyclerView=(RecyclerView) findViewById(R.id.upcomingMovies);
        RecyclerView.LayoutManager mLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        upComingMoviesRecyclerView.setLayoutManager(mLayoutManager3);
        upComingMoviesRecyclerView.setAdapter(upcomingMoviesAdapter);
        upcomingMoviesAdapter.notifyDataSetChanged();

        popularActressRecyclerView=(RecyclerView) findViewById(R.id.popularactresses);
        RecyclerView.LayoutManager mLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popularActressRecyclerView.setLayoutManager(mLayoutManager4);
        popularActressRecyclerView.setAdapter(popularActresses);
        popularActresses.notifyDataSetChanged();


        popularActorsRecyclerView=(RecyclerView) findViewById(R.id.popularactors);
        RecyclerView.LayoutManager mLayoutManager5=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popularActorsRecyclerView.setLayoutManager(mLayoutManager5);
        popularActorsRecyclerView.setAdapter(popularActors);
        popularActors.notifyDataSetChanged();






        loadJSON();
        loadJSONForTopMovies();
        loadJSONForTopGrossedMovies();
        loadJsonForUpcomingMovies();
        loadJSONforpopularActresses();
        loadJSONforpopularActors();

    }

    private void loadJSONforpopularActors() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<ActorActresses>> call=apiService.getActors(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<ActorActresses>>() {
                @Override
                public void onResponse(Call<List<ActorActresses>> call, Response<List<ActorActresses>> response) {
                    List<ActorActresses> topMovies=response.body();
                    popularActorsRecyclerView.setAdapter(new ActorsActressAdapter(getApplicationContext(),topMovies));
                    popularActorsRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<ActorActresses>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJSONforpopularActresses() {

        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<ActorActresses>> call=apiService.getActresses(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<ActorActresses>>() {
                @Override
                public void onResponse(Call<List<ActorActresses>> call, Response<List<ActorActresses>> response) {
                    List<ActorActresses> topMovies=response.body();
                    popularActressRecyclerView.setAdapter(new ActorsActressAdapter(getApplicationContext(),topMovies));
                    popularActressRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<ActorActresses>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    private void loadJsonForUpcomingMovies() {

        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<MoviesPosterDetails>> call=apiService.getUpcomingMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<MoviesPosterDetails>>() {
                @Override
                public void onResponse(Call<List<MoviesPosterDetails>> call, Response<List<MoviesPosterDetails>> response) {
                    List<MoviesPosterDetails> topMovies=response.body();
                    upComingMoviesRecyclerView.setAdapter(new TopMoviesAdapter(getApplicationContext(),topMovies));
                    upComingMoviesRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<MoviesPosterDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    private void loadJSONForTopGrossedMovies() {

        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<MoviesPosterDetails>> call=apiService.getTopGrossedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<MoviesPosterDetails>>() {
                @Override
                public void onResponse(Call<List<MoviesPosterDetails>> call, Response<List<MoviesPosterDetails>> response) {
                    List<MoviesPosterDetails> topGrossedMovies=response.body();
                    topGrossedMoviesRecyclerView.setAdapter(new TopMoviesAdapter(getApplicationContext(),topGrossedMovies));
                    topGrossedMoviesRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<MoviesPosterDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJSONForTopMovies() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<MoviesPosterDetails>> call=apiService.getTopMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<MoviesPosterDetails>>() {
                @Override
                public void onResponse(Call<List<MoviesPosterDetails>> call, Response<List<MoviesPosterDetails>> response) {
                    List<MoviesPosterDetails> topMovies=response.body();
                    recyclerView1.setAdapter(new TopMoviesAdapter(getApplicationContext(),topMovies));
                    recyclerView1.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<MoviesPosterDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    private void loadJSON() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<Trailer>> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<Trailer>>() {
                @Override
                public void onResponse(Call<List<Trailer>> call, Response<List<Trailer>> response) {
                    List<Trailer> movies = response.body();
                    recyclerView.setAdapter(new ToolbarAdapter(getApplicationContext(), movies));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<Trailer>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();

                }
            });}
         catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout=(AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange==-1)
                {
                    scrollRange=appBarLayout.getTotalScrollRange();
                }
                if(scrollRange+verticalOffset==0)
                {
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow=true;
                }
                else if(isShow)
                {
                    collapsingToolbarLayout.setTitle("");
                    isShow=false;


                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent=new Intent(MainActivity.this,SearchActivity.class);
        intent.putExtra("name",query);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
