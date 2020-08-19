package com.example.schoolforproducer.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolforproducer.Interfaces.IItemClick;
import com.example.schoolforproducer.Model.YoutubePreview;

import com.example.schoolforproducer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class YouubePreviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<YoutubePreview> youtubePreviews;
    Context context;
    IItemClick iItemClick;
    private int VIEW_TYPE_ITEM = 0;
    private int VIEW_TYPE_PROGRESS = 1;
    public YouubePreviewAdapter(Context context, ArrayList<YoutubePreview> ids, IItemClick iItemClick){
        this.context = context;
        this.youtubePreviews = ids;
        this.iItemClick = iItemClick;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.podcast_item, parent, false);
            return new PodcastHolder(v, iItemClick);
        }else if(viewType == VIEW_TYPE_PROGRESS){

            View itemView = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingHolder(itemView);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return youtubePreviews.size();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof PodcastHolder) {
            ((PodcastHolder) holder).title.setText(youtubePreviews.get(position).getTitle());
            ((PodcastHolder) holder).youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.cueVideo(youtubePreviews.get(position).getIds(), 0);

                    Log.d("ids" + position, youtubePreviews.get(position).getIds());
                }
            });
        }else if(holder instanceof  LoadingHolder){
            ((LoadingHolder) holder).progressBar.setIndeterminate(true);
        }
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
    public class LoadingHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return youtubePreviews.get(position) == null ? VIEW_TYPE_PROGRESS:VIEW_TYPE_ITEM;
    }
}
