package com.example.schoolforproducer.Query;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.schoolforproducer.Interfaces.ArticlesCallback;
import com.example.schoolforproducer.Interfaces.VolleyCallBack;
import com.example.schoolforproducer.Model.Articles;
import com.example.schoolforproducer.Model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticlesDAO {

        public void getArticles(String url, RequestQueue
        queue, final ArticlesCallback articlesCallback){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                    ,url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray articles = response.getJSONArray("articles");
                        for(int i = 0; i <5; i ++){
                           JSONObject article = (JSONObject) articles.get(i);
                            String author = article.getString("author");
                            String title = article.getString("title");
                            String urlToImage = article.getString("urlToImage");
                            String content = article.getString("content");
                            Articles articlesObj = new Articles(title,content,author,urlToImage);
                            articlesCallback.passArticles(articlesObj);
                        }
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

