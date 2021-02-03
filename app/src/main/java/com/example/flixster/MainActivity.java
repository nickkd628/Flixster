package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
 
public class MainActivity extends AppCompatActivity {

    public static final String Now_Playing_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";
    List<movie>movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();
        //create the adapter
       MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        //set the adapter on recycler view
        rvMovies.setAdapter(movieAdapter);

        //set a layout manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        AsyncHttpClient client = new  AsyncHttpClient ();
        client.get(Now_Playing_URL, new JsonHttpResponseHandler() {
            @Override
            //int I is used a statuecode
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"On Sucess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "results: "+results);
                   movies.addAll(movie.fromJSONArray(results));
                   movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "moives: "+ movies.size());
                } catch (JSONException e) {
                  Log.e(TAG, "Hit json exception",e);
                }
            }

            @Override
            //int I is used a statuecode
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG,"On fail");
            }
        });
    }
}