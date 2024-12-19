package com.example.moviesapplication.movieapi;

import com.example.moviesapplication.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<Result> getPopularMovie(@Query("api_key") String apiKey);

}
