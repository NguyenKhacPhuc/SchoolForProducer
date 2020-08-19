package com.example.schoolforproducer.EntertainmentSubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.schoolforproducer.Adapter.YouubePreviewAdapter;
import com.example.schoolforproducer.Common.BasedFragment;
import com.example.schoolforproducer.HandleRequest.QueryKeywords;
import com.example.schoolforproducer.HandleRequest.YoutubeConfig;
import com.example.schoolforproducer.Interfaces.IItemClick;
import com.example.schoolforproducer.Model.YoutubePreview;
import com.example.schoolforproducer.R;
import com.example.schoolforproducer.UniversitySubFragments.InspirationFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class VideosFragment extends BasedFragment implements IItemClick {
    View v;
    RequestQueue queue;
    ArrayList<YoutubePreview> ids;
    RecyclerView rs;
    YouubePreviewAdapter youubePreviewAdapter;
    private String url = YoutubeConfig.getSCHEME()+ QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PLDi4L6c8M53bPS9FO4Hb-t87IArYbSjlx"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.video_frag,container,false);
       initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        youubePreviewAdapter = new YouubePreviewAdapter(v.getContext(),ids, VideosFragment.this);
        rs.setAdapter(youubePreviewAdapter);
        fetchData(linearLayoutManager,rs,url,queue, youubePreviewAdapter,ids);
       return v;
    }
    void initView(){
        queue = Volley.newRequestQueue(getContext());
        ids = new ArrayList<>();
        rs = v.findViewById(R.id.recycler_videos);

    }

    @Override
    protected void fetchData(LinearLayoutManager layoutManager,RecyclerView rs,String url, RequestQueue queue, YouubePreviewAdapter youubePreviewAdapter, ArrayList<YoutubePreview> ids) {
        super.fetchData(layoutManager,rs,url, queue, youubePreviewAdapter, ids);
    }

    @Override
    public void onClick(int postition, YouTubePlayerView youTubePlayerView) {

    }
}
