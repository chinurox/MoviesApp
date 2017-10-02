package com.example.gargc.cinemalyticsmoviesapp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gargc.cinemalyticsmoviesapp.Adapter.ActorsActressAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.ActressesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.TopMoviesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Api.Client;
import com.example.gargc.cinemalyticsmoviesapp.Api.Service;
import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorActressActivity extends AppCompatActivity {

    List<MoviesPosterDetails> allMoviesList;

    RecyclerView moviesRecyclerView;

    TopMoviesAdapter allMoviesAdapter;

    List<ActorActresses> filelist;

    int position;

    ImageView imageview;
    TextView dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_actress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCollapsingToolbar();


        position=getIntent().getIntExtra("position",0);
        int number=getIntent().getIntExtra("number",0);

        if(number==1)
        {
            filelist= ActorsActressAdapter.moviesPosterDetailsList;
        }
        else if(number==2)
        {
            filelist= ActressesAdapter.moviesPosterDetailsList;
        }

        imageview=(ImageView) findViewById(R.id.actorsphoto);
        dob=(TextView) findViewById(R.id.actordateofbirth);

        Picasso.with(this).load(filelist.get(position).getProfilepath()).into(imageview);
        dob.setText(filelist.get(position).getDateofbirth()+" "+"|"+" "+filelist.get(position).getBirthplace());



        allMoviesList=new ArrayList<>();

        allMoviesAdapter=new TopMoviesAdapter(this,allMoviesList);

        moviesRecyclerView=(RecyclerView) findViewById(R.id.staggeredrecyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        moviesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        moviesRecyclerView.setAdapter(allMoviesAdapter);
        allMoviesAdapter.notifyDataSetChanged();

        loadAllMoviesJson();

    }

    private void loadAllMoviesJson() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<MoviesPosterDetails>> call=apiService.getMoviesOfActor(filelist.get(position).getId(),10,BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<MoviesPosterDetails>>() {
                @Override
                public void onResponse(Call<List<MoviesPosterDetails>> call, Response<List<MoviesPosterDetails>> response) {
                    List<MoviesPosterDetails> topMovies=response.body();
                    moviesRecyclerView.setAdapter(new TopMoviesAdapter(getApplicationContext(),topMovies));
                    moviesRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<MoviesPosterDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                        Toast.makeText(ActorActressActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }














    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar2);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout=(AppBarLayout) findViewById(R.id.appbar2);
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
}
