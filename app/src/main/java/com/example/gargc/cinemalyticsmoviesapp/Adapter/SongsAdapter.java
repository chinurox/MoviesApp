package com.example.gargc.cinemalyticsmoviesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.gargc.cinemalyticsmoviesapp.R;
import com.example.gargc.cinemalyticsmoviesapp.customview.Circulartextview;
import com.example.gargc.cinemalyticsmoviesapp.model.ActorActresses;
import com.example.gargc.cinemalyticsmoviesapp.model.SongsDetails;
import com.example.gargc.cinemalyticsmoviesapp.model.SongsForMovies;
import com.example.gargc.cinemalyticsmoviesapp.model.VideoForTrailer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    Context mContext;
    public static List<SongsDetails> moviesPosterDetailsList;

    public SongsAdapter(Context mContext,List<SongsDetails> moviesPosterDetailsList)
    {
        this.mContext=mContext;
        this.moviesPosterDetailsList=moviesPosterDetailsList;

    }

    @Override
    public SongsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsAdapter.MyViewHolder holder, int position) {

        holder.textView.setText(moviesPosterDetailsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return moviesPosterDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        Button button;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.songstitle);
            button=(Button) itemView.findViewById(R.id.playbutton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext, SongsForMovies.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });


        }
    }
}
