package com.example.moviesdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moviesdb.adapters.MovieRecyclerView;
import com.example.moviesdb.adapters.OnMovieListener;
import com.example.moviesdb.models.MovieModel;
import com.example.moviesdb.request.Service;
import com.example.moviesdb.response.MovieSearchResponse;
import com.example.moviesdb.utils.MovieApi;
import com.example.moviesdb.utils.credentials;
import com.example.moviesdb.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMovieListener {


    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;

    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView =findViewById(R.id.recyclerView);
        setContentView(R.layout.activity_main);


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        searchMovieApi("Fast");


        //remove
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                searchMovieApi("Fast");
//            }
//        });
    }


    private void ObserveAnyChange()
    {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null)
                {
                    for(MovieModel movieModel:movieModels)
                    {
                        Log.v("Tag","onCLick"+movieModel.getTitle());

                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }


    private void searchMovieApi(String query)
    {
        movieListViewModel.searchMovieApi(query);
    }

    private void ConfigureRecyclerView()
    {
        movieRecyclerAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


//    private void GetRetrofitResponse() {
//        MovieApi movieApi = Service.getMovieapi();
//
//        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
//                credentials.API_KEY,
//                "Big Hero"
//        );
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if(response.code()==200)
//                {
//                    Log.v("Tag","the response"+ response.body().toString());
//
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//                     for(MovieModel movie:movies)
//                     {
//                         Log.v("Tag","The title"+ movie.getTitle());
//                     }
//                }
//                else
//                    {
//                        try {
//                            Log.v("Tag","Error"+response.body().toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void GetRetrofitResponseAccordingToID()
//    {
//        MovieApi movieApi = Service.getMovieapi();
//        Call<MovieModel> responseCall = movieApi.getMovie(
//                550,
//                credentials.API_KEY
//        );
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if(response.code() == 200)
//                {
//                    MovieModel movie= response.body();
//                    Log.v("Tag","The response"+movie.getTitle());
//                }else
//                    {
//                        try {
//                            Log.v("Tag","Error"+response.body());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}