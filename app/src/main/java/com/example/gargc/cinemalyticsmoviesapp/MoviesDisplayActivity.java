package com.example.gargc.cinemalyticsmoviesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.gargc.cinemalyticsmoviesapp.Adapter.ActorsActressAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.SongsAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Adapter.TopMoviesAdapter;
import com.example.gargc.cinemalyticsmoviesapp.Api.Client;
import com.example.gargc.cinemalyticsmoviesapp.Api.Service;
import com.example.gargc.cinemalyticsmoviesapp.customview.ExpandableTextView;
import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;

import com.example.gargc.cinemalyticsmoviesapp.model.SongsDetails;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDisplayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Intent intent;
    int position;

    ImageView imageView;
    TextView textview;
    VideoView videoView;

    public static final String API_KEY="AIzaSyAw-LIojxDu-yGlP32Z4VApmzny-bPAytU";
    public static  String VIDEO_ID;



    public static List<MoviesPosterDetails> list;
    List<ActorActresses> castList;
    List<SongsDetails> songsList;



    RecyclerView castRecyclerView;
    RecyclerView songsRecyclerView;

    ActorsActressAdapter castAdapter;
    SongsAdapter songsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_display);
        intent=getIntent();

        position=intent.getIntExtra("position",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        //setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list=TopMoviesAdapter.moviesPosterDetailsList;

        initCollapsingToolbar();
        initviews();

    }

    private void initviews() {
        imageView=(ImageView) findViewById(R.id.imageofmovie);
        textview=(TextView) findViewById(R.id.moviesdetails);
       // videoView=(VideoView) findViewById(R.id.videoViewForTrailer);
        YouTubePlayerView youTubePlayer=(YouTubePlayerView) findViewById(R.id.videoViewForTrailer);
        youTubePlayer.initialize(API_KEY,this);

        assignViews();

    }


    private void assignViews() {
        Picasso.with(this).load(list.get(position).getPosterPath()).into(imageView);
        textview.setText(list.get(position).getReleaseYear()+" "+"|"+" "+TopMoviesAdapter.moviesPosterDetailsList.get(position).getGenre()+" "+"|"+" "+TopMoviesAdapter.moviesPosterDetailsList.get(position).getRegion());



        ExpandableTextView synopsis=(ExpandableTextView) findViewById(R.id.synopsis);
        synopsis.setText(list.get(position).getDescription());

        castList=new ArrayList<>();
        songsList=new ArrayList<>();

        castRecyclerView=(RecyclerView) findViewById(R.id.cast);
        songsRecyclerView=(RecyclerView) findViewById(R.id.songs1);

        castAdapter=new ActorsActressAdapter(this,castList);
        songsAdapter=new SongsAdapter(this,songsList);

        RecyclerView.LayoutManager mLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        castRecyclerView.setLayoutManager(mLayoutManager4);
        castRecyclerView.setAdapter(castAdapter);
        castAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        songsRecyclerView.setLayoutManager(mLayoutManager1);
        songsRecyclerView.setAdapter(songsAdapter);
        songsAdapter.notifyDataSetChanged();
        
        

        loadJSON();
        loadJSONforSongs();



    }

    private void loadJSONforSongs() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<SongsDetails>> call=apiService.getSongs(list.get(position).getId(),BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<SongsDetails>>() {
                @Override
                public void onResponse(Call<List<SongsDetails>> call, Response<List<SongsDetails>> response) {
                    List<SongsDetails> topMovies=response.body();
                    songsRecyclerView.setAdapter(new SongsAdapter(getApplicationContext(),topMovies));
                    songsRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<SongsDetails>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MoviesDisplayActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
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
            Service apiService = Client.getClient().create(Service.class);
            Call<List<ActorActresses>> call=apiService.getCast(list.get(position).getId(),BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<List<ActorActresses>>() {
                @Override
                public void onResponse(Call<List<ActorActresses>> call, Response<List<ActorActresses>> response) {
                    List<ActorActresses> topMovies=response.body();
                    castRecyclerView.setAdapter(new ActorsActressAdapter(getApplicationContext(),topMovies));
                    castRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<ActorActresses>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MoviesDisplayActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }

            });}
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar1);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout=(AppBarLayout) findViewById(R.id.appbar1);
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
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        String a=list.get(position).getTrailerLink();
        String d="";
        String c="";
        for(int i=a.length()-1;a.charAt(i)!='=';i--)
        {
            d=d+a.charAt(i);
        }
        for(int i=d.length()-1;i>=0;i--)
        {
            c=c+d.charAt(i);

        }
        Log.i("video",c);
        youTubePlayer.loadVideo(c);


    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener=new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener=new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
