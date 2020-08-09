package com.example.schoolforproducer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schoolforproducer.Model.YoutubePreview;

import com.google.android.youtube.player.YouTubeThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastHolder> {
    ArrayList<YoutubePreview> youtubePreviews;
    Context context;
    IItemClick iItemClick;

    public PodcastAdapter(Context context, ArrayList<YoutubePreview> ids, IItemClick iItemClick){
        this.context = context;
        this.youtubePreviews = ids;
        this.iItemClick = iItemClick;
    }
    @NonNull
    @Override
    public PodcastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.podcast_item,parent,false);
        return new PodcastHolder(v,iItemClick);
    }

    @Override
    public int getItemCount() {
        return youtubePreviews.size();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull PodcastHolder holder, final int position) {
            holder.title.setText(youtubePreviews.get(position).getTitle());
            holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.cueVideo(youtubePreviews.get(position).getIds(),0);
                    Log.d("ids"+position,youtubePreviews.get(position).getIds());
                }
            });
    }

    public static class PodcastHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        YouTubePlayerView youTubePlayerView;
        IItemClick iItemClick;
        public PodcastHolder(@NonNull View itemView, IItemClick iItemClick) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.player_view);
            this.iItemClick = iItemClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.onClick(getAdapterPosition(),youTubePlayerView);
        }
    }
}
