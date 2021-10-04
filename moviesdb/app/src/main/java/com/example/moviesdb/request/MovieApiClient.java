package com.example.moviesdb.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesdb.AppExecutors;
import com.example.moviesdb.models.MovieModel;
import com.example.moviesdb.response.MovieResponse;
import com.example.moviesdb.response.MovieSearchResponse;
import com.example.moviesdb.utils.MovieApi;
import com.example.moviesdb.utils.credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    public static MovieApiClient getInstance()
    {
        if(instance==null)
        {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient()
    {
        mMovies = new MutableLiveData<>();
    }
    public LiveData<List<MovieModel>> getMovies()
    {
        return mMovies;
    }


    public void searchMovieApi(String query)
    {
        if(retrieveMoviesRunnable !=null)
        {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        },4000, TimeUnit.MILLISECONDS);



    }

    private class RetrieveMoviesRunnable implements Runnable
    {
    private String query;
    boolean cancel = false;

        public RetrieveMoviesRunnable(String query) {
            this.query = query;
        }

        @Override
        public void run() {

            try
            {
                Response response = getMovies(query).execute();
                if(cancel)
                {
                    return;
                }
                if(response.code()==200)
                {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    mMovies.postValue(list);
                }else
                    {
                        Log.v("Tag","Error"+response.errorBody());
                        mMovies.postValue(null);
                    }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMovies(String query)
        {
            return Service.getMovieapi().searchMovie(
                    credentials.API_KEY,
                    query
            );
        }

        private void cancelRequest()
        {
            Log.v("Tag","Cancel request");
            cancel = true;
        }
    }
}
