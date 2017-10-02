package com.example.gargc.cinemalyticsmoviesapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.gargc.cinemalyticsmoviesapp.ActorActressActivity;
import com.example.gargc.cinemalyticsmoviesapp.MoviesDisplayActivity;
import com.example.gargc.cinemalyticsmoviesapp.R;
import com.example.gargc.cinemalyticsmoviesapp.customview.Circulartextview;
import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActorsActressAdapter extends RecyclerView.Adapter<ActorsActressAdapter.MyViewHolder> {

    Context mContext;
   public static List<ActorActresses> moviesPosterDetailsList;

    public ActorsActressAdapter(Context mContext,List<ActorActresses> moviesPosterDetailsList)
    {
        this.mContext=mContext;
        this.moviesPosterDetailsList=moviesPosterDetailsList;

    }

    @Override
    public ActorsActressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewformoviespart2,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorsActressAdapter.MyViewHolder holder, int position) {

        Picasso.with(mContext).load(moviesPosterDetailsList.get(position).getProfilepath()).into(holder.imageView);
        holder.title.setText(moviesPosterDetailsList.get(position).getName());
        holder.circulartextview.setText(Float.toString(moviesPosterDetailsList.get(position).getRating()));
        holder.ratingBar.setRating((moviesPosterDetailsList.get(position).getRating()));



    }

    @Override
    public int getItemCount() {
        return moviesPosterDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView title;
        RatingBar ratingBar;
        Circulartextview circulartextview;


        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.imageviewformovieposter2);
            title=(TextView) itemView.findViewById(R.id.titleofmovie2);
            ratingBar=(RatingBar) itemView.findViewById(R.id.ratingbar2);
            circulartextview=(Circulartextview) itemView.findViewById(R.id.circularviewforrating1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,  ActorActressActivity.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.putExtra("number",1);
                    //intent.putExtra("mylist", (Serializable) moviesPosterDetailsList);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
