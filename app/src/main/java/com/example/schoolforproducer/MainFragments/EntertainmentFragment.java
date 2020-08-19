package com.example.schoolforproducer.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.schoolforproducer.Common.BasedFragment;
import com.example.schoolforproducer.EntertainmentSubFragments.MemeFragment;
import com.example.schoolforproducer.EntertainmentSubFragments.VideosFragment;
import com.example.schoolforproducer.PagerAdapters.EntertainmentPagerAdapter;
import com.example.schoolforproducer.R;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class EntertainmentFragment extends BasedFragment {
    View v;
    TabLayout tabLayout;
    ViewPager viewPager;
    HashMap<String, Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.entertainment_fragment,container,false);
        initView();
        addData();
        assert getFragmentManager() != null;
        viewPager.setAdapter(new EntertainmentPagerAdapter(getFragmentManager(),0,fragments));
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }
    void initView(){
        tabLayout = v.findViewById(R.id.tabLayoutEntertainment);
        viewPager = v.findViewById(R.id.viewPager);
        fragments = new HashMap<>();
    }
    void addData(){

        fragments.put("MEMES",new MemeFragment());
        fragments.put("VIDEOS", new VideosFragment());
    }
}
