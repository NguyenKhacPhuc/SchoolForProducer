package com.example.schoolforproducer.Services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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
    private IBinder iBinder = new MediaBinder();
    MediaPlayer mediaPlayer;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String url = intent.getStringExtra("url");
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return iBinder;
    }
    public void play(){

            mediaPlayer.start();

    }
    public void paused(){

            mediaPlayer.pause();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seekTo(long milisec){
        mediaPlayer.seekTo(milisec,MediaPlayer.SEEK_CLOSEST);
    }
    public class MediaBinder extends Binder{
        public MusicPlayService getService(){
            return MusicPlayService.this;
        }
    }
}

