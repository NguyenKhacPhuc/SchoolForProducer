package com.example.schoolforproducer.Query;

import android.media.MediaPlayer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.schoolforproducer.Interfaces.VolleyCallBack;
import com.example.schoolforproducer.Model.Track;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class TrackDAO {
     Random rand = new Random() ;
    public void getRandomMusic(String url, RequestQueue queue, final VolleyCallBack volleyCallBack){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                ,url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray items = response.getJSONArray("items");
                    int randomNo  = rand.nextInt(items.length());
                    JSONObject jsonObject = (JSONObject) items.get(randomNo);
                    JSONObject snippet = jsonObject.getJSONObject("snippet");
                    JSONObject resourceId = snippet.getJSONObject("resourceId");
                    String trackName = snippet.getString("title");
                    String channelTitle = snippet.getString("channelTitle");
                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                    JSONObject defaultThumb = thumbnails.getJSONObject("high");
                    String thumbnailUrl = defaultThumb.getString("url");
                    String id = resourceId.getString("videoId");
                    Track track = new Track(id,thumbnailUrl,trackName,channelTitle);
                    volleyCallBack.getArray(track);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }
}
