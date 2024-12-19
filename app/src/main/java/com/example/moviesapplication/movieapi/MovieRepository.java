package com.example.moviesapplication.movieapi;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapplication.R;
import com.example.moviesapplication.model.Movie;
import com.example.moviesapplication.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie> movieList = new ArrayList<>();
    private MutableLiveData<List<Movie>> liveMovieList = new MutableLiveData<>();

    private Application application;

    public MovieRepository(Application application){
        this.application = application;
    }

    public MutableLiveData<List<Movie>> setMovieList(){
        MovieService movieService = RetrofitInstance.getRetrofitInstance();

        Call<Result> call = movieService.getPopularMovie(application.getApplicationContext().getString(R.string.apiKey));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result res = response.body();
                if(res!=null && res.getResults()!=null){
                    movieList = (ArrayList<Movie>) res.getResults();
                    liveMovieList.setValue(res.getResults());
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });

        return liveMovieList;
    }


}
