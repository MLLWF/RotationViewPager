package com.mllwf.viewpagerimageviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/11.
 */

public abstract class BaseGuideFm extends Fragment {

    private View rootView;
    private GuideAdapter mAdapter;
    private ViewPager mViewPager;

    protected LinearLayout llPoint;
    protected List<Bitmap> mBitmapList = new ArrayList<>();
    private List<ImageView> imgViews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_guide_view, container, false);
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_guide);
        llPoint = (LinearLayout) rootView.findViewById(R.id.ll_point);
        setBitmapDatas();
        initimgGroup(mBitmapList);
        mAdapter = new GuideAdapter(imgViews);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < llPoint.getChildCount(); i++) {
                    if (i == position) {
                        llPoint.getChildAt(i).setSelected(true);
                    } else {
                        llPoint.getChildAt(i).setSelected(false);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 0://什么都没做  空闲状态
                        break;
                    case 1://正在滑动
                        break;
                    case 2://滑动完毕
                        break;
                }
            }
        });
        return rootView;
    }


    private void initimgGroup(List<Bitmap> bitmapList) {
        if (bitmapList == null || bitmapList.size() == 0) {
            return;
        }
        for (int i = 0; i < bitmapList.size(); i++) {
            //TODO: initImgGroup
            intiImgGroup(bitmapList, i);
            //TODO: initPointGroup
            initPointGroup(i);
        }
    }

    private void intiImgGroup(List<Bitmap> bitmapList, int i) {
        ImageView img = new ImageView(getContext());
        img.setImageBitmap(bitmapList.get(i));
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        imgViews.add(img);
    }

    private void initPointGroup(int i) {
        ImageView point = new ImageView(getContext());
        point.setBackgroundResource(R.drawable.shape_point_selector);
        if (i == 0) {
            point.setSelected(true);
        } else {
            point.setSelected(false);
        }
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parms.leftMargin = 20;
        point.setLayoutParams(parms);
        llPoint.addView(point);
    }

    public abstract void setBitmapDatas();
}



