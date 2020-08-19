package com.example.schoolforproducer.EntertainmentSubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolforproducer.Adapter.ImageAdapter;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class MemeFragment extends Fragment {
    View v;
    RecyclerView rs;
    ArrayList<String> urls;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meme_frag,container,false);
        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        fetchData();
        rs.setAdapter(new ImageAdapter(getContext(),urls));
        return v;
    }
    void initView(){
        rs = v.findViewById(R.id.recycler_memes);
        urls = new ArrayList<>();
    }
    void fetchData(){
        urls.add("https://i.pinimg.com/564x/01/d2/0e/01d20e717a6c29cf4f6096561bd7239d.jpg");
        urls.add("https://i.pinimg.com/564x/18/70/42/18704257199a40effc5a3718998a8d9e.jpg");
        urls.add("https://i.pinimg.com/564x/8a/bd/a8/8abda87bde3216a7cc738cb8ea369cc7.jpg");
        urls.add("https://i.pinimg.com/564x/4b/f5/6e/4bf56e61f49030d0fa9be3160d145348.jpg");
        urls.add("https://i.pinimg.com/564x/7c/f4/91/7cf49183f734cad41874cc919d85438d.jpg");
    }
}
