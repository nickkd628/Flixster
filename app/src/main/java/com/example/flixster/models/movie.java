package com.example.flixster.models;

import android.graphics.Movie;

import com.example.flixster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class movie{


    int movieId;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
// empty constructor needed by the Parceler library
    public movie(){

    }

    public movie (JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title =jsonObject.getString("title");
        overview= jsonObject.getString("overview");
        rating=  jsonObject.getDouble("vote_average");
        movieId= jsonObject.getInt("id");

    }


    public static List<movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException {
        List<movie> movies = new ArrayList<>();
        for (int i =0; i <movieJsonArray.length(); i ++){
            movies.add(new movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }
    public String getBackdropPath (){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }
}
