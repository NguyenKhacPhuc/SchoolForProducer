package com.example.schoolforproducer.Common;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.schoolforproducer.Model.YoutubePreview;
import com.example.schoolforproducer.Adapter.YouubePreviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class BasedFragment extends Fragment {
    Random rand ;
    static int position = 0;

        protected void fetchData(final LinearLayoutManager linearLayoutManager,RecyclerView rs,String url, RequestQueue queue, final YouubePreviewAdapter youubePreviewAdapter, final ArrayList<YoutubePreview> ids){
            rand = new Random();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        JSONArray items = response.getJSONArray("items");

                        for (int i = 0; i < 5 ;i++){
                            int randomNo = rand.nextInt(items.length());
                            JSONObject element = (JSONObject) items.get(randomNo);
                            JSONObject snippet = element.getJSONObject("snippet");
                            JSONObject resourceId = snippet.getJSONObject("resourceId");
                            String title = snippet.getString("title");
                            String iD = resourceId.getString("videoId");
                            YoutubePreview youTubePlayerView = new YoutubePreview(iD,title);
                            ids.add(youTubePlayerView);

                        }

                        youubePreviewAdapter.notifyDataSetChanged();
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
