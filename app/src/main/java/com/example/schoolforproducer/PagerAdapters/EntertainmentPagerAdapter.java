package com.example.schoolforproducer.PagerAdapters;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.schoolforproducer.Common.PagerAdapter;

import java.util.HashMap;

public class EntertainmentPagerAdapter  extends PagerAdapter {
    public EntertainmentPagerAdapter(@NonNull FragmentManager fm, int behavior, HashMap<String, Fragment> fragmentHashMap) {
        super(fm, behavior, fragmentHashMap);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return super.getItem(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
