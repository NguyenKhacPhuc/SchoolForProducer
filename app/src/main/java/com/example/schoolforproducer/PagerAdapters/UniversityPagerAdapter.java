package com.example.schoolforproducer.PagerAdapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.schoolforproducer.UniversitySubFragments.ArticlesUniFragment;
import com.example.schoolforproducer.UniversitySubFragments.InspirationFragment;
import com.example.schoolforproducer.UniversitySubFragments.PodcastsFragment;
import com.example.schoolforproducer.UniversitySubFragments.TutotrialsFragment;

import java.util.ArrayList;

public class UniversityPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> nameOfFragment;
    private ArticlesUniFragment articlesUniFragment;
    private InspirationFragment inspirationFragment;
    private PodcastsFragment podcastsFragment;
    private TutotrialsFragment tutotrialsFragment;
    //Constructor for FragmentStatePagerAdapter.
    // If BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT=1 is passed in, then only the current Fragment is in the Lifecycle.State.RESUMED state,
    // while all other fragments are capped at Lifecycle.State.STARTED.
    // If BEHAVIOR_SET_USER_VISIBLE_HINT=0 is passed, all fragments are in the Lifecycle.State.RESUMED state
    // and there will be callbacks to Fragment.setUserVisibleHint(boolean)
    public UniversityPagerAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<String> nameOfFragment) {
        super(fm, behavior);
        this.nameOfFragment = nameOfFragment;
        articlesUniFragment = new ArticlesUniFragment();
        inspirationFragment = new InspirationFragment();
        podcastsFragment = new PodcastsFragment();
        tutotrialsFragment = new TutotrialsFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return articlesUniFragment;
            case 1:
                return podcastsFragment;
            case 2:
                return tutotrialsFragment;
            case 3:
                return inspirationFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nameOfFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nameOfFragment.get(position);
    }
}
