package com.example.schoolforproducer.MainFragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.schoolforproducer.Constant;
import com.example.schoolforproducer.Interfaces.IMediaChecker;
import com.example.schoolforproducer.Query.Randoming;
import com.example.schoolforproducer.R;
import com.example.schoolforproducer.Services.MusicPlayService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RadioFragment extends Fragment implements View.OnClickListener {
    View v;
    CircleImageView circleImageView;

    TextView channelName;
    MusicPlayService musicPlayService;
    TextView trackName;
    SeekBar seekBar;
    ImageButton next;
    ServiceConnection serviceConnection;
    ImageButton previous;
    ImageButton play;
    boolean isPlay;
    TextView durationStart;
    TextView durationEnd;
    CustomReceiver customReceiver;
    Randoming randoming;
    RequestQueue requestQueue;
    private CountDownTimer countDownTimer;
    long length;
    long temp;
    private Long defaultDuration = 0L;
    long currentLength;
    long currentMil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.radio_fragment,container,false);
        initView();
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        play.setOnClickListener(this);
        playMusic();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                if(countDownTimer != null && fromUser){
                    
                    currentMil = progress*1000;
                    musicPlayService.seekTo(currentMil);
                    currentLength = temp-currentMil;
                    countDownTimer.cancel();
                    countDownTimer = new CountDownTimer(currentLength,1000) {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int current = seekBar.getProgress();
                            currentLength = currentLength-1000;
                            currentMil = currentMil+1000;
                            seekBar.setProgress(current+1);
                            String lengthStr = convertToSecond(currentLength);
                            String defaultStr = convertToSecond(currentMil);
                            durationStart.setText(defaultStr);
                            durationEnd.setText(lengthStr);
                        }

                        @Override
                        public void onFinish() {
                            Objects.requireNonNull(getActivity()).unbindService(serviceConnection);
                            playMusic();
                        }
                    }.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;

    }
    void initView(){
        circleImageView = (CircleImageView) v.findViewById(R.id.thumbnail);
        channelName = (TextView) v.findViewById(R.id.channel_name);
        trackName = (TextView) v.findViewById(R.id.track_name);
        seekBar = (SeekBar) v.findViewById(R.id.seekbar);
        next = (ImageButton) v.findViewById(R.id.next);
        previous = (ImageButton) v.findViewById(R.id.previous);
        requestQueue = Volley.newRequestQueue(getContext());
        play = (ImageButton) v.findViewById(R.id.play);
        durationStart = (TextView) v.findViewById(R.id.durationStart);
        durationEnd = (TextView) v.findViewById(R.id.durationEnd);
        randoming = new Randoming(Constant.URL,requestQueue,getContext());
        isPlay = false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.next:
            case R.id.previous:
               Objects.requireNonNull(getActivity()).unbindService(serviceConnection);
                playMusic();
                musicPlayService.play();
                break;
            case R.id.play:
                if(isPlay){
                    play.setImageResource(R.drawable.pause);
                    isPlay = false;
                    musicPlayService.play();


                }else{
                    play.setImageResource(R.drawable.play);
                    isPlay= true;
                    musicPlayService.paused();
                }
                break;
            default:
                Toast.makeText(getContext(),String.valueOf(v.getId()),Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        customReceiver = new CustomReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Track");
        Objects.requireNonNull(getActivity()).registerReceiver(customReceiver,intentFilter);
    }
    void playMusic(){
         serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicPlayService.MediaBinder mediaBinder = (MusicPlayService.MediaBinder) service;
                musicPlayService = mediaBinder.getService();
                musicPlayService.play();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        randoming.getRandomMusic(new IMediaChecker() {
            @Override
            public void check(String url) {
                Intent intent = new Intent(getContext(),MusicPlayService.class);
                intent.putExtra("url",url);
                Objects.requireNonNull(getActivity()).bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

            }
        });


    }
    class CustomReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            assert bundle != null;
            String titleString = bundle.getString("title");
            String channelStringName = bundle.getString("channelName");
            String thumbnailStr = bundle.getString("thumbnail");
            length = bundle.getLong("duration");
            temp = length;
            channelName.setText(channelStringName);
            trackName.setText(titleString);
            Glide.with(context).load(thumbnailStr).into(circleImageView);
            durationEnd.setText(convertToSecond(length));
            durationStart.setText(convertToSecond(0L));
            countDown();
        }
        void countDown(){
            final long max = length*1000;
            defaultDuration = 0L;
            seekBar.setProgress(0);
            seekBar.setMax((int) (length/1000));
            if(countDownTimer != null){
                countDownTimer.cancel();
            }
            countDownTimer = new CountDownTimer(length,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    int current = seekBar.getProgress();
                    seekBar.setProgress(current+1);
                    length = length - 1000;
                    defaultDuration = defaultDuration + 1000;
                    String lengthStr = convertToSecond(length);
                    String defaultStr = convertToSecond(defaultDuration);
                    durationStart.setText(defaultStr);
                    durationEnd.setText(lengthStr);
                }

                @Override
                public void onFinish() {
                    Objects.requireNonNull(getActivity()).unbindService(serviceConnection);
                    playMusic();
                }
            }.start();

        }

    }
    public String convertToSecond(Long duration){
        @SuppressLint("DefaultLocale") String hms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        return hms;
    }

}


