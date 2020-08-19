package com.example.schoolforproducer.Query;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.android.volley.RequestQueue;
import com.example.schoolforproducer.Constant;
import com.example.schoolforproducer.Interfaces.IMediaChecker;
import com.example.schoolforproducer.Interfaces.VolleyCallBack;
import com.example.schoolforproducer.Model.Track;

import java.io.IOException;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class Randoming {
    private String url;
    private RequestQueue requestQueue;
    private Context context;
    public Randoming(final String url, final RequestQueue requestQueue, final Context context){
        this.url = url;
       this.requestQueue = requestQueue;
        this.context = context;
    }
    public String getRandomMusic(final IMediaChecker iMediaChecker){
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.getRandomMusic(url, requestQueue, new VolleyCallBack() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void getArray(final Track track) {
                String link = Constant.YOUTUBE_LINK+track.getId();
                new YouTubeExtractor(context) {
                    @Override
                    protected void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta videoMeta) {

                        YtFile ytFile = ytFiles.get(140);
                        Intent intent = new Intent();
                        intent.setAction("Track");
                        Bundle bundle = new Bundle();
                        bundle.putString("thumbnail",track.getThumbnail());
                        bundle.putString("channelName", track.getTrackChannel());
                        bundle.putString("title",track.getTrackName());
                        bundle.putLong("duration",videoMeta.getVideoLength()*1000);
                        intent.putExtras(bundle);
                        context.sendBroadcast(intent);

                        String url = ytFile.getUrl().replace("\\","");
                        iMediaChecker.check(url);
                    }
                }.extract(link,true,true);
            }
        });
        return null;
    }
}
