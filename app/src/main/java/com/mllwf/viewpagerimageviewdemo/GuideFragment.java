package com.mllwf.viewpagerimageviewdemo;

import android.graphics.BitmapFactory;

/**
 * Created by ML on 2017/4/11.
 */

public class GuideFragment extends BaseGuideFm {
    @Override
    public void setBitmapDatas() {
       int[] imgIds = new int[]{R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
        for (int i = 0; i < imgIds.length; i++) {
            mBitmapList.add(BitmapFactory.decodeResource(getResources(), imgIds[i]));
        }
    }
}
