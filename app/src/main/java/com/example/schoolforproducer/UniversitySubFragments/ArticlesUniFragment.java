package com.example.schoolforproducer.UniversitySubFragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.schoolforproducer.Adapter.ArticlesTitleAdapter;
import com.example.schoolforproducer.Adapter.ContentAdapter;
import com.example.schoolforproducer.Constant;
import com.example.schoolforproducer.Interfaces.ArticlesCallback;
import com.example.schoolforproducer.Interfaces.TittleItemClick;
import com.example.schoolforproducer.Model.Articles;
import com.example.schoolforproducer.Query.ArticlesDAO;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class ArticlesUniFragment extends Fragment implements TittleItemClick {
    RecyclerView titles;
    ArrayList<String> titlesLst;
    RecyclerView content;
    ArticlesTitleAdapter articlesTitleAdapter;
    ArrayList<Articles> articleLst;
    String url = Constant.BASED_URL + "top-headlines?" + Constant.queryCountry + "us" + Constant.apiKeyQuery + Constant.NewsApi;
    private View view;
    ContentAdapter contentAdapter;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_uni_frag,container,false);
        initView();
        fetchData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        titles.setLayoutManager(linearLayoutManager);
        content.setLayoutManager(linearLayoutManager1);
        articlesTitleAdapter = new ArticlesTitleAdapter(getContext(),titlesLst,this);
        contentAdapter = new ContentAdapter(getContext(),articleLst);
        content.setAdapter(contentAdapter);
        titles.setAdapter(articlesTitleAdapter);
        return view;
    }

    public void initView(){
        titles = (RecyclerView) view.findViewById(R.id.uni_articles_brief_recy) ;
        titlesLst = new ArrayList<>();
        articleLst = new ArrayList<>();
        content = view.findViewById(R.id.contentRecycler);
        requestQueue = Volley.newRequestQueue(getContext());
    }
    public void fetchData(){
        ArticlesDAO articlesDAO = new ArticlesDAO();
        articlesDAO.getArticles(url, requestQueue, new ArticlesCallback() {
            @Override
            public void passArticles(Articles articles) {
                titlesLst.add(articles.getTitle());
                articleLst.add(articles);
                articlesTitleAdapter.notifyItemInserted(titlesLst.size());
                contentAdapter.notifyItemInserted(articleLst.size());
            }

        });
    }

    @Override
    public void getTitlePosition(int position) {
        content.scrollToPosition(position);
    }
}
