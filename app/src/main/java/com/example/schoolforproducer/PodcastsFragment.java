package com.example.schoolforproducer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PodcastsFragment extends Fragment {
    View view;
    ArrayList<String> ids;
    RecyclerView rs;
    PodcastAdapter podcastAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.podcasts_frag,container,false);
         initView();
         fetchData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
         podcastAdapter = new PodcastAdapter(getContext(),ids);
        rs.setAdapter(podcastAdapter);
        Log.d("run", "run");
        return view;
    }
    void initView(){
        ids = new ArrayList<>();
        rs = (RecyclerView)view.findViewById(R.id.recycler_podcast);
    }
    void fetchData(){
        ids.add("OX3xtVPRh8c");
        ids.add("OX3xtVPRh8c");
        ids.add("OX3xtVPRh8c");
        ids.add("OX3xtVPRh8c");
        ids.add("OX3xtVPRh8c");
        ids.add("OX3xtVPRh8c");
    }
}
