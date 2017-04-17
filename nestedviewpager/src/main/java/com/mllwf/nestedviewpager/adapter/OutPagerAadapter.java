package com.mllwf.nestedviewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/14.
 */

public class OutPagerAadapter extends FragmentPagerAdapter {

    private List<Fragment> mList = new ArrayList<>();

    public OutPagerAadapter(FragmentManager fm ,List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
