package com.example.simpleplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Provider;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Songs>songsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        RecyclerView rvSongs = findViewById(R.id.songs_recycle_view);

        // Initialize songs
        songsArrayList=new ArrayList<>();
        for(int i=0;i<30;i++)
            songsArrayList.add(new Songs("Begin you htyuldkiuy thdwq lqeqjeqwk mazen","efwejf",new Songs.Thumbnails(120,90,"https://i.ytimg.com/vi/zrFI2gJSuwA/default.jpg")));

        // Create adapter passing in the sample user data
        SongsAdapter adapter = new SongsAdapter(songsArrayList);

        // Attach the adapter to the recyclerview to populate items
        rvSongs.setAdapter(adapter);

        // Set layout manager to position the items
        rvSongs.setLayoutManager(new LinearLayoutManager(this));


        /*final SearchView mainSearchView=findViewById(R.id.song_search_view_main);
        mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(),mainSearchView.getQuery().toString(),Toast.LENGTH_SHORT).show();
*//*                getResponseFromYouTubeAPIAndDownload(
                        Request.Method.GET,
                        ShortCuts.URL+"part=snippet&q="+encodeValue(mainSearchView.getQuery().toString())+"key="+ShortCuts.YOUTUBE_API_KEY,
                        new VolleyCallback() {
                            @Override
                            public void onSuccessResponse(String response) throws JSONException {
                                JSONObject obj= new JSONObject(response);
                            }
                        });
                getResponseFromYouTubeAPIAndDownload(
                        Request.Method.GET,
                        ShortCuts.MP3_AUDIO+"video id",
                        new VolleyCallback() {
                            @Override
                            public void onSuccessResponse(String response) throws JSONException {
                                JSONObject obj= new JSONObject(response);
                            }
                        });*//*
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //Toast.makeText(getApplicationContext(),mainSearchView.getQuery().toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
    }
    public void getResponseFromYouTubeAPIAndDownload(
            int method,
            String url,
            final VolleyCallback callback) {
        StringRequest stringRequest =
                new StringRequest(
                        method,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    callback.onSuccessResponse(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                NetworkResponse networkResponse = error.networkResponse;
                                String errorMessage = "Unknown error";
                                if (networkResponse == null) {
                                    if (error.getClass().equals(TimeoutError.class)) {
                                        errorMessage = "Request timeout";
                                    } else if (error.getClass().equals(NoConnectionError.class)) {
                                        errorMessage = "Failed to connect server";
                                    }
                                } else {
                                    String result = new String(networkResponse.data);
                                    try {
                                        JSONObject response = new JSONObject(result);
                                        String status = response.getString("status");
                                        String message = response.getString("message");

                                        Log.e("Error Status", status);
                                        Log.e("Error Message", message);

                                        if (networkResponse.statusCode == 404) {
                                            errorMessage = "Resource not found";
                                        } else if (networkResponse.statusCode == 500) {
                                            errorMessage = message+" Something is getting wrong";
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Log.i("Error", errorMessage);
                                error.printStackTrace();
                            }
                        }) {};
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

}
