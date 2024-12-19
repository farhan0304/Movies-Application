package com.example.moviesapplication.movieapi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviesapplication.model.Movie;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MovieRepository repository;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getMovieList(){
        return repository.setMovieList();
    }
}
