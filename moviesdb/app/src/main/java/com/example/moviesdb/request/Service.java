package com.example.moviesdb.request;

import com.example.moviesdb.utils.MovieApi;
import com.example.moviesdb.utils.credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieapi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieapi()
    {
        return movieapi;
    }
}
