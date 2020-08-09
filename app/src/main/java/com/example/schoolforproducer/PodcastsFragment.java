package com.example.schoolforproducer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.schoolforproducer.Model.YoutubePreview;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import com.google.android.youtube.player.YouTubeThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Objects;

public class PodcastsFragment extends Fragment implements IItemClick {
    View view;
    ArrayList<YoutubePreview> ids;
    RecyclerView rs;
    PodcastAdapter podcastAdapter;
    RequestQueue queue;
    private String url = YoutubeConfig.getSCHEME()+QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PL3osQJLUr9gIZrGxgrs-uxomQvR1URRTb"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi()
            +QueryKeywords.getMAX()+"50";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.podcasts_frag,container,false);

         initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        podcastAdapter = new PodcastAdapter(view.getContext(),ids,PodcastsFragment.this);
        rs.setAdapter(podcastAdapter);
        fetchData();
        Log.d("run", "run");

        return view;
    }
    void initView(){
        queue = Volley.newRequestQueue(getContext());
        rs = (RecyclerView)view.findViewById(R.id.recycler_podcast);
        ids = new ArrayList<>();
    }
    void fetchData(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int position = 0;
                    JSONArray items = response.getJSONArray("items");
                    for (int i = position; i < 7;i++){
                        JSONObject element = (JSONObject) items.get(i);
                        JSONObject snippet = element.getJSONObject("snippet");
                        JSONObject resourceId = snippet.getJSONObject("resourceId");
                        String title = snippet.getString("title");
                        String iD = resourceId.getString("videoId");
                        YoutubePreview youTubePlayerView = new YoutubePreview(iD,title);
                        ids.add(youTubePlayerView);
                    }
                    podcastAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(final int postition, YouTubePlayerView youTubePlayerView) {

    }
}
