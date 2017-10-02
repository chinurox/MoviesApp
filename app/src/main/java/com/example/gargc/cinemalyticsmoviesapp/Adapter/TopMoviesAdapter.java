package com.example.gargc.cinemalyticsmoviesapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.gargc.cinemalyticsmoviesapp.MoviesDisplayActivity;
import com.example.gargc.cinemalyticsmoviesapp.R;
import com.example.gargc.cinemalyticsmoviesapp.customview.Circulartextview;
import com.example.gargc.cinemalyticsmoviesapp.model.MoviesPosterDetails;
import com.example.gargc.cinemalyticsmoviesapp.model.VideoForTrailer;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MyViewHolder> {
    Context mContext;
    public static List<MoviesPosterDetails> moviesPosterDetailsList;

    public TopMoviesAdapter(Context mContext,List<MoviesPosterDetails> moviesPosterDetailsList)
    {
        this.mContext=mContext;
        this.moviesPosterDetailsList=moviesPosterDetailsList;
    }


    @Override
    public TopMoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewformoviespart2,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopMoviesAdapter.MyViewHolder holder, int position) {
        Picasso.with(mContext).load(moviesPosterDetailsList.get(position).getPosterPath()).into(holder.imageView);
        holder.title.setText(moviesPosterDetailsList.get(position).getTitle());
        //holder.timer.setText(moviesPosterDetailsList.get(position).getRuntime());
       // holder.movietype.setText(moviesPosterDetailsList.get(position).getGenre());
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
           // timer=(TextView) itemView.findViewById(R.id.timertime);
           // movietype=(TextView) itemView.findViewById(R.id.categorymovietype);
            ratingBar=(RatingBar) itemView.findViewById(R.id.ratingbar2);
            circulartextview=(Circulartextview) itemView.findViewById(R.id.circularviewforrating1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,  MoviesDisplayActivity.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.putExtra("number",1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
