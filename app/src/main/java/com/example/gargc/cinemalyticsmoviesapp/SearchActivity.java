package com.example.gargc.cinemalyticsmoviesapp;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gargc.cinemalyticsmoviesapp.Adapter.TopMoviesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Api.Client;
import com.example.gargc.cinemalyticsmoviesapp.Api.Service;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    String querry;
    List<MoviesPosterDetails> moviesPosterDetailsList;

    TopMoviesAdapter searchMoviesAdaper;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent=getIntent();
        querry=intent.getStringExtra("name");
        Log.i("name",querry);

        moviesPosterDetailsList=new ArrayList<>();

        searchMoviesAdaper=new TopMoviesAdapter(this,moviesPosterDetailsList);

        recyclerView=(RecyclerView) findViewById(R.id.staggeredrecyclerview1);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(searchMoviesAdaper);
        searchMoviesAdaper.notifyDataSetChanged();

        loadJson();


    }

    private void loadJson() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<MoviesPosterDetails>> call=apiService.getSearchedMovied(querry,BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<MoviesPosterDetails>>() {
                @Override
                public void onResponse(Call<List<MoviesPosterDetails>> call, Response<List<MoviesPosterDetails>> response) {
                    List<MoviesPosterDetails> topMovies=response.body();
                    recyclerView.setAdapter(new TopMoviesAdapter(getApplicationContext(),topMovies));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<MoviesPosterDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(SearchActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

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
        Intent intent=new Intent(SearchActivity.this,SearchActivity.class);
        intent.putExtra("name",query);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
