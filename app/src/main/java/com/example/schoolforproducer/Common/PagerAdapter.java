package com.example.schoolforproducer.Common;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PagerAdapter extends FragmentStatePagerAdapter {
    HashMap<String,Fragment> fragmentHashMap;
    List<String> indexes ;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, HashMap<String,Fragment> fragmentHashMap) {
        super(fm, behavior);
        this.fragmentHashMap = fragmentHashMap;
        indexes = new ArrayList<>(fragmentHashMap.keySet());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Objects.requireNonNull(fragmentHashMap.get(indexes.get(position)));
    }

    @Override
    public int getCount() {
        return indexes.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return indexes.get(position);
    }
}
