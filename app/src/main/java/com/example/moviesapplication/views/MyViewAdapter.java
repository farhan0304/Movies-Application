package com.example.moviesapplication.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapplication.R;
import com.example.moviesapplication.databinding.MovieListItemBinding;
import com.example.moviesapplication.model.Movie;

import java.util.ArrayList;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder> {

    ArrayList<Movie> arrayList;

    public MyViewAdapter(ArrayList<Movie> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding mli = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false
        );
        return new MyViewHolder(mli);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = arrayList.get(position);
        holder.mli.setMovie(movie);
        Glide.with(holder.posterPreview.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath())
                .into(holder.posterPreview);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void addMovie(Movie movie){
        arrayList.add(movie);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        MovieListItemBinding mli;
        ImageView posterPreview;


        public MyViewHolder(@NonNull MovieListItemBinding mli) {
            super(mli.getRoot());
            this.mli = mli;
            posterPreview = mli.posterImage;

        }
    }
}
