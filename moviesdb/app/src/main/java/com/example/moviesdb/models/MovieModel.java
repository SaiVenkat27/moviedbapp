package com.example.moviesdb.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel {

    private int movie_id;
    private String poster_path;
    private String overview;
    private String release_date;
    private String title;
    private float vote_average;
    private int runtime;

    //constructor
    public MovieModel(int movie_id,String poster_path,String overview,String release_date,String title,float vote_average,int runtime)
    {
        this.movie_id = movie_id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.runtime = runtime;
    }

    protected MovieModel(Parcel in) {
        movie_id = in.readInt();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readFloat();
        runtime= in.readInt();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //getters
    public int getMovie_id()
    {
        return movie_id;
    }
    public String getPoster_path()
    {
        return poster_path;
    }
    public  String getOverview()
    {
        return overview;
    }
    public String getRelease_date()
    {
        return release_date;
    }
    public String getTitle()
    {
        return title;
    }
    public float getVote_average()
    {
        return vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movie_id);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(title);
        parcel.writeFloat(vote_average);
    }
}
