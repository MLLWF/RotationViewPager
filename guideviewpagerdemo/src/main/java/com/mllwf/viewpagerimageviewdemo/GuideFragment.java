package com.mllwf.viewpagerimageviewdemo;

import android.graphics.BitmapFactory;

/**
 * Created by ML on 2017/4/11.
 */

public class GuideFragment extends BaseGuideFm {
    @Override
    public void setBitmapDatas() {
        int[] imgIds = new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
        for (int i = 0; i < imgIds.length; i++) {
            mBitmapList.add(BitmapFactory.decodeResource(getResources(), imgIds[i]));
        }
    }
}
