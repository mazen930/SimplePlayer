package com.example.simpleplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Songs>songsArrayList;
    LinearLayoutManager linearLayoutManager;
    SongsAdapter adapter;
    RecyclerView rvSongs;
    public static boolean endOfResult=false,loadingMore=false;
    public static String url,title;
    private static final int REQUEST_CODE = 1234;
    Long startIndex = 0L;
    Long offset = 10L;
    int totalItemCount=0,lastVisibleItem,visibleThreshold=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        rvSongs = findViewById(R.id.songs_recycle_view);

        //get the linear layout
        linearLayoutManager= new LinearLayoutManager(this);
        rvSongs.setLayoutManager(linearLayoutManager);

        rvSongs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition();
                if (!loadingMore
                        && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    new LoadMoreItemsTask((Activity) recyclerView.getContext()).execute();
                    recyclerView.setFocusable(lastVisibleItem);
                    loadingMore=false;
                }
            }
            });
        // Initialize songs
        songsArrayList=new ArrayList<>();
        for(int i=0;i<5;i++)
            songsArrayList.add(new Songs("Begin you htyuldkiuy thdwq lqeqjeqwk mazen","efwejf",new Songs.Thumbnails(120,90,"https://i.ytimg.com/vi/zrFI2gJSuwA/default.jpg")));

        // Create adapter passing in the sample user data
        adapter = new SongsAdapter(songsArrayList);

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
    private  class LoadMoreItemsTask extends AsyncTask<Void, Void, List<Songs>> {

        private Activity activity;

        private LoadMoreItemsTask(Activity activity) {
            this.activity =  activity;
            loadingMore = true;
        }

        @Override
        protected void onPreExecute() {
            if(endOfResult){
                loadingMore=true;
                Toast.makeText(getApplicationContext(),"No more result",Toast.LENGTH_LONG).show();
            }else {
                loadingMore=false;
            }
            super.onPreExecute();
        }

        @Override
        protected List<Songs> doInBackground(Void... voids) {

            try {
                return getNextItems(startIndex, offset);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private List<Songs> getNextItems(Long startIndex, Long offset) throws IOException, JSONException {
            ArrayList<Songs>arr=new ArrayList<>();
            /*try {
                // converting response to json object
                JSONObject obj = getJSONObjectFromURL(ULRConnection.url+"/search/query?query="+encodeValue(editText.getText().toString())+"&img=0"+"&page="+ (currentPage+1));
                // if no error in response
                // getting the result from the response
                JSONArray searchResult = obj.getJSONArray("result");
                for(int i=0;i<searchResult.length();i++) {
                    Songs currentSong=new Songs();
                    JSONObject current = searchResult.getJSONObject(i);
                    arr.add(currentSong);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(arr.size()==0){
                endOfResult=true;
                loadingMore=true;
            }
            else{
                endOfResult=false;
            }*/
            for(int i=0;i<5;i++)
                arr.add(new Songs("Begin you htyuldkiuy thdwq lqeqjeqwk mazen","efwejf",new Songs.Thumbnails(120,90,"https://i.ytimg.com/vi/zrFI2gJSuwA/default.jpg")));
            return  arr;
        }

        @Override
        protected void onPostExecute(List<Songs> listItems) {
            if (listItems.size() > 0) {
                startIndex = startIndex + listItems.size();
                setItems(listItems);
            }else{
                loadingMore=true;
                Toast.makeText(getApplicationContext(),"No more Result",Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
            super.onPostExecute(listItems);
        }

        private void setItems(List<Songs> listItems) {
            songsArrayList.addAll(listItems);
        }
    }
    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
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
