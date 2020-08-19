package com.example.schoolforproducer.Services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.schoolforproducer.Constant;
import com.example.schoolforproducer.HandleRequest.QueryKeywords;
import com.example.schoolforproducer.HandleRequest.YoutubeConfig;
import com.example.schoolforproducer.Interfaces.VolleyCallBack;
import com.example.schoolforproducer.Model.Track;
import com.example.schoolforproducer.Query.TrackDAO;

import java.io.IOException;
import java.security.Provider;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MusicPlayService extends Service {
    private String url = YoutubeConfig.getSCHEME()+ QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PLDfKAXSi6kUZnATwAUfN6tg1dULU-7XcD"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi()
            +QueryKeywords.getMAX()+"50";
    MediaPlayer mediaPlayer;

    RequestQueue requestQueue;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Testing Service",Toast.LENGTH_SHORT).show();
        mediaPlayer = new MediaPlayer();
         requestQueue = Volley.newRequestQueue(this);
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.getRandomMusic(url, requestQueue, new VolleyCallBack() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void getArray(final Track track) {
                String link = Constant.YOUTUBE_LINK+track.getId();
                new YouTubeExtractor(getApplicationContext()) {
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
                        sendBroadcast(intent);

                        String url = ytFile.getUrl().replace("\\","");
                        try {
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("url player",url);

                    }
                }.extract(link,true,true);
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
