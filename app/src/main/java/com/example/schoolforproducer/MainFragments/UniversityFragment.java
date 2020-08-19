package com.example.schoolforproducer.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.schoolforproducer.PagerAdapters.UniversityPagerAdapter;
import com.example.schoolforproducer.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class UniversityFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    View v;
    ArrayList<String> nameOfTheFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.university_fragment,container,false);
        initView();
        addNameTab();
        assert getFragmentManager() != null;
        viewPager.setAdapter(new UniversityPagerAdapter(getFragmentManager(),0,nameOfTheFragment));
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }
    void initView(){
        nameOfTheFragment = new ArrayList<>();
        tabLayout = v.findViewById(R.id.tabLayoutUniversity);
        viewPager = v.findViewById(R.id.viewPager);
    }
    private void addNameTab(){
        nameOfTheFragment.add("Articles");
        nameOfTheFragment.add("Podcasts");
        nameOfTheFragment.add("Tutorials");
        nameOfTheFragment.add("Inspiration");
    }

}
