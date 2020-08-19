package com.example.schoolforproducer.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.schoolforproducer.Common.BasedFragment;
import com.example.schoolforproducer.PagerAdapters.DealsPagerAdapter;
import com.example.schoolforproducer.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DealsFragment extends BasedFragment {
    View v;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<String> nameOfTheFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fire_deals_fragment,container,false);
        initView();
        addName();
        assert getFragmentManager() != null;
        viewPager.setAdapter(new DealsPagerAdapter(getFragmentManager(),0,nameOfTheFragment));
        tabLayout.setupWithViewPager(viewPager);
        return  v;
    }
    void initView(){
        nameOfTheFragment = new ArrayList<>();
        tabLayout = v.findViewById(R.id.tabLayoutDeals);
        viewPager = v.findViewById(R.id.viewPager);
    }
    void addName(){
        nameOfTheFragment.add("Top 10");
        nameOfTheFragment.add("Sales");
        nameOfTheFragment.add("Latest");
    }
}
