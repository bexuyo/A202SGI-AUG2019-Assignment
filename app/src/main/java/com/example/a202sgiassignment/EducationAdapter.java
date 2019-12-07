package com.example.a202sgiassignment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class EducationAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public EducationAdapter(FragmentManager fm, int numOfTabs)
    {
        super(fm);

        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0: return new MathFragment();
            case 1: return new ScienceFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
