package com.example.flixster.adapters;


import android.content.Context;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.DetailActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.movie;
import com.google.android.material.shape.RoundedCornerTreatment;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<movie> movies;

    public MovieAdapter(Context context, List<movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //inflating a layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
       View movieView= LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    //populating data into the item  through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " +position);
        //get movie at the position
        movie movie = movies.get(position);
        //Bind the movie data
        holder.bind(movie);
    }

    //returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container= itemView.findViewById(R.id.container);

        }

        public void bind(movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String ImageUrl;


            //if phone is in landscape mode
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                ImageUrl = movie.getBackdropPath();
            }
            else{
                ImageUrl = movie.getPosterPath();
            }

                //added circleCrop need to resize how it looks
            Glide.with(context).load(ImageUrl).transform(new CircleCrop()).into(ivPoster);

            // 1. register click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 2 Navigate to a new activity
                   Intent i = new Intent(context, DetailActivity.class);

                   i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((AppCompatActivity)
                                    context, (View)ivPoster, "profile");
                   context.startActivity(i, options.toBundle());

                }

            });



        }
    }
}
