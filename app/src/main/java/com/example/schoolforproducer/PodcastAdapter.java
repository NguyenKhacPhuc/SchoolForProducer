package com.example.schoolforproducer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastHolder> {
    ArrayList<String> ids;
    Context context;
    private String getUrl(String s){
        String result = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<iframe width=\"420\" height=\"345\" src=\"https://www.youtube.com/embed/tgbNymZ7vqY\">\n" +
                "</iframe>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return result;
    }
    public PodcastAdapter(Context context, ArrayList<String> ids){
        this.context = context;
        this.ids = ids;
    }
    @NonNull
    @Override
    public PodcastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.podcast_item,parent,false);
        return new PodcastHolder(v);
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull PodcastHolder holder, int position) {

        holder.webview.setVisibility(View.VISIBLE);
        holder.webview.getSettings().setJavaScriptEnabled(true);
        holder.webview.getSettings().setLoadWithOverviewMode(true);
        holder.webview.getSettings().setUseWideViewPort(true);
        holder.webview.loadData(getUrl(ids.get(position)),"text/html", "UTF-8");
    }

    public static class PodcastHolder extends RecyclerView.ViewHolder{
        WebView webview;
        public PodcastHolder(@NonNull View itemView) {
            super(itemView);
            webview = itemView.findViewById(R.id.webview);
        }
    }
}
