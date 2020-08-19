package com.example.schoolforproducer.PagerAdapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.schoolforproducer.DealsSubFragment.LatestFragment;
import com.example.schoolforproducer.DealsSubFragment.SalesFragment;
import com.example.schoolforproducer.DealsSubFragment.TopTenFragment;

import java.util.ArrayList;

public class DealsPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> nameOfTheSubFragment;
    TopTenFragment topTenFragment;
    SalesFragment dealsFragment;
    LatestFragment latestFragment;
    public DealsPagerAdapter(@NonNull FragmentManager fm, int behavior,ArrayList<String> nameOfTheSubFragment) {
        super(fm, behavior);
        this.nameOfTheSubFragment = nameOfTheSubFragment;
        topTenFragment = new TopTenFragment();
        dealsFragment = new SalesFragment();
        latestFragment = new LatestFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return topTenFragment;
            case 1:
                return dealsFragment;
            case 2:
                return latestFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nameOfTheSubFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nameOfTheSubFragment.get(position);
    }
}
