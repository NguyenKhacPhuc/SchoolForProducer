package com.example.schoolforproducer.DealsSubFragment;

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
import com.example.schoolforproducer.Interfaces.IItemClick;
import com.example.schoolforproducer.Model.YoutubePreview;
import com.example.schoolforproducer.Common.BasedFragment;
import com.example.schoolforproducer.Adapter.YouubePreviewAdapter;
import com.example.schoolforproducer.HandleRequest.QueryKeywords;
import com.example.schoolforproducer.R;
import com.example.schoolforproducer.HandleRequest.YoutubeConfig;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class LatestFragment extends BasedFragment implements IItemClick {
    View v;
    ArrayList<YoutubePreview> ids;
    RequestQueue requestQueue;
    RecyclerView rs;
    YouubePreviewAdapter youubePreviewAdapter;
    private String url = YoutubeConfig.getSCHEME()+ QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PLuSCvVqeUNqOylssWv0H3qxfiuydxTaGb"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi()
            +QueryKeywords.getMAX()+"50";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.latest_frag,container,false);
        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        youubePreviewAdapter = new YouubePreviewAdapter(v.getContext(),ids,LatestFragment.this);
        rs.setAdapter(youubePreviewAdapter);
        fetchData(linearLayoutManager,rs,url,requestQueue, youubePreviewAdapter,ids);
        return v;
    }
    void initView(){
        ids = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        rs = v.findViewById(R.id.latest_fragment);
    }

    @Override
    protected void fetchData(LinearLayoutManager linearLayoutManager,RecyclerView rs,String url, RequestQueue queue, YouubePreviewAdapter youubePreviewAdapter, ArrayList<YoutubePreview> ids) {
        super.fetchData(linearLayoutManager,rs,url, queue, youubePreviewAdapter, ids);
    }

    @Override
    public void onClick(int postition, YouTubePlayerView youTubePlayerView) {

    }
}
