package com.mllwf.nestedviewpager.fragment.outlayer;

import android.graphics.Color;
import android.support.design.widget.TabLayout;

import com.mllwf.nestedviewpager.BaseViewPagerFragmentActivity;
import com.mllwf.nestedviewpager.fragment.innerlayer.InnerFragmentFour;
import com.mllwf.nestedviewpager.fragment.innerlayer.InnerFragmentOne;
import com.mllwf.nestedviewpager.fragment.innerlayer.InnerFragmentThree;
import com.mllwf.nestedviewpager.fragment.innerlayer.InnerFragmentTwo;

/**
 * Created by ML on 2017/4/14.
 */

public class OutFragmentOne extends BaseViewPagerFragmentActivity {

    private InnerFragmentOne mFragmentOne;


    public void initFragmentList() {
        mFragmentOne = new InnerFragmentOne();
        mFragmentList.add(mFragmentOne);
        mFragmentList.add(new InnerFragmentTwo());
        mFragmentList.add(new InnerFragmentThree());
        mFragmentList.add(new InnerFragmentFour());
    }

    @Override
    public void initTabTitleOrCustormTabItemViewList() {
        mTitleList.add("推荐");
        mTitleList.add("收藏");
        mTitleList.add("爱好");
        mTitleList.add("音乐");
    }

    @Override
    public void initTabIconList() {

    }

    @Override
    public void initTabLayoutStyleList() {
        mTabLayout.setBackgroundColor(Color.YELLOW);
        mPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initCustormTabItemView() {

    }

    @Override
    public void configSelectedCustormTabItemViewStyle(TabLayout.Tab tab) {

    }

    @Override
    public void configNoSelectedCustormTabItemViewStyle(TabLayout.Tab tab) {

    }
}
