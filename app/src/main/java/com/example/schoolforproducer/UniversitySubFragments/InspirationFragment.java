package com.example.schoolforproducer.UniversitySubFragments;

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

public class InspirationFragment extends BasedFragment implements IItemClick {
    View view;
    ArrayList<YoutubePreview> ids;
    RecyclerView rs;
    YouubePreviewAdapter youubePreviewAdapter;
    RequestQueue queue;
    private String url = YoutubeConfig.getSCHEME()+ QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PLx5i827-FDqNXDQs_yMyaf59uPZalCa2W"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi()
            +QueryKeywords.getMAX()+"50";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inspiration_frag,container,false);

        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        youubePreviewAdapter = new YouubePreviewAdapter(view.getContext(),ids,InspirationFragment.this);
        rs.setAdapter(youubePreviewAdapter);
        fetchData(linearLayoutManager,rs,url,queue, youubePreviewAdapter,ids);

        return view;
    }
    void initView(){
        ids = new ArrayList<>();
        rs = (RecyclerView)view.findViewById(R.id.recyclerFrag_tut);
        queue = Volley.newRequestQueue(getContext());
    }

    @Override
    protected void fetchData(LinearLayoutManager linearLayoutManager,RecyclerView recyclerView,String url, RequestQueue queue, YouubePreviewAdapter youubePreviewAdapter, ArrayList<YoutubePreview> ids) {
        super.fetchData(linearLayoutManager,rs,url, queue, youubePreviewAdapter, ids);
    }

    @Override
    public void onClick(int postition, YouTubePlayerView youTubePlayerView) {

    }
}
