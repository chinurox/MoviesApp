package com.example.gargc.cinemalyticsmoviesapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gargc.cinemalyticsmoviesapp.R;
import com.example.gargc.cinemalyticsmoviesapp.model.Trailer;
import com.example.gargc.cinemalyticsmoviesapp.model.VideoForTrailer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ToolbarAdapter extends RecyclerView.Adapter<ToolbarAdapter.MyViewHolder> {

    Context mContext;
    public static List<Trailer> trailerLink;

    public ToolbarAdapter(Context mContext,List<Trailer> trailerLink)
    {
        this.mContext=mContext;
        this.trailerLink=trailerLink;
    }
    @Override
    public ToolbarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.videoview,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToolbarAdapter.MyViewHolder holder, int position) {
        //Uri uri= Uri.parse(trailerLink.get(position).getTrailerLink());
       /* holder.videoView.setVideoURI(uri);
        MediaController mc = new MediaController(mContext);
        mc.setAnchorView(holder.videoView);
        mc.setMediaPlayer(holder.videoView);
        holder.videoView.setMediaController(mc);
        holder.videoView.setVideoURI(uri);
        */
        Picasso.with(mContext).load(trailerLink.get(position).getPosterPath()).into(holder.imageView);
        Log.i("image",trailerLink.get(position).getPosterPath());
        Log.i("video",trailerLink.get(position).getTrailerLink());
    }

    @Override
    public int getItemCount() {
        return trailerLink.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        VideoView videoView;
        ImageView imageView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            videoView=(VideoView)itemView.findViewById(R.id.videoView);
            imageView=(ImageView) itemView.findViewById(R.id.imageOfTrailer);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.INVISIBLE);
                    //videoView.setVisibility(View.VISIBLE);
                    //Uri uri= Uri.parse(trailerLink.get(getAdapterPosition()).getTrailerLink());
                    //MediaController mc = new MediaController(mContext);
                    //mc.setAnchorView(videoView);
                    //mc.setFocusable(true);
                    //mc.setMediaPlayer(videoView);
                    //videoView.setMediaController(mc);
                    //videoView.setVideoURI(uri);
                    Log.i("video1",trailerLink.get(getAdapterPosition()).getTrailerLink());
                  //  videoView.start();

                    Intent intent=new Intent(mContext, VideoForTrailer.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
