package com.example.gargc.cinemalyticsmoviesapp.model;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gargc.cinemalyticsmoviesapp.Adapter.ToolbarAdapter;
import com.example.gargc.cinemalyticsmoviesapp.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

public class VideoForTrailer  extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    WebView webView;
    public static final String API_KEY="AIzaSyAw-LIojxDu-yGlP32Z4VApmzny-bPAytU";
    public static  String VIDEO_ID;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_for_trailer);
        Intent intent=getIntent();
         p=intent.getIntExtra("position",0);

      /*  webView=(WebView) findViewById(R.id.videoview1);
        webView.setWebViewClient(new Callback());

        Intent intent=getIntent();
        int p=intent.getIntExtra("position",0);
        webView.loadUrl(ToolbarAdapter.trailerLink.get(p).getTrailerLink());
        WebSettings ref=webView.getSettings(); // its used to enable javascript
        ref.setJavaScriptEnabled(true);

        */
        YouTubePlayerView youTubePlayer=(YouTubePlayerView) findViewById(R.id.videoViewForTrailer1);
        youTubePlayer.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        String a=ToolbarAdapter.trailerLink.get(p).getTrailerLink();
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

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

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


   /* private  class Callback extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);

        }
    }*/

}