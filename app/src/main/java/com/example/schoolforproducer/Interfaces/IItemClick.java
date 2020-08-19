package com.example.schoolforproducer.Interfaces;

import android.widget.ImageView;


import com.google.android.youtube.player.YouTubeThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public interface IItemClick {
    void onClick(int postition,  YouTubePlayerView youTubePlayerView);
}
