package com.example.schoolforproducer.UniversitySubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolforproducer.Adapter.ArticlesTitleAdapter;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class ArticlesUniFragment extends Fragment {
    RecyclerView titles;
    ArrayList<String> titlesLst;
    ArticlesTitleAdapter articlesTitleAdapter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_uni_frag,container,false);
        initView();
        fetchData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        titles.setLayoutManager(linearLayoutManager);
        articlesTitleAdapter = new ArticlesTitleAdapter(getContext(),titlesLst);
        titles.setAdapter(articlesTitleAdapter);
        return view;
    }

    public void initView(){
        titles = (RecyclerView) view.findViewById(R.id.uni_articles_brief_recy) ;
        titlesLst = new ArrayList<>();
    }
    public void fetchData(){
        for(int x = 0; x < 5;x++){
            titlesLst.add("This time you’ll be competing using our amazing synth sounds from: Fantasy!");
        }
    }
}
