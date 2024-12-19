package com.example.moviesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.moviesapplication.databinding.ActivityMainBinding;
import com.example.moviesapplication.model.Movie;
import com.example.moviesapplication.movieapi.MyViewModel;
import com.example.moviesapplication.views.MyViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movieArrayList = new ArrayList<>();

    ActivityMainBinding activityMainBinding;

    MyViewAdapter adapter;

    MyViewModel myViewModel;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        swipeRefreshLayout = activityMainBinding.swiperLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        getPopularMovies();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPopularMovies();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);


            }
        });


    }

    public void getPopularMovies(){
        myViewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieArrayList = (ArrayList<Movie>) movies;
                loadRecyclerView();
            }
        });


    }
    public void loadRecyclerView(){
        recyclerView = activityMainBinding.recyclerView;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyViewAdapter(movieArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}