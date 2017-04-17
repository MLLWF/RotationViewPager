package com.mllwf.nestedviewpager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mllwf.nestedviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/17.
 */

public class NomalRotationView extends FrameLayout implements ViewPager.OnPageChangeListener {

    // TODO: 2017/4/12  界面样式参数
    private int pointLeftMargin = 20;

    private Context mContext;
    private ViewPager mPager;
    private LinearLayout mPointLayout;

    private List<Bitmap> mBitmaps = new ArrayList<>();
    private List<ImageView> mImageViews = new ArrayList<>();
    private List<ImageView> mPoints = new ArrayList<>();
    private boolean isOnlyOne = false;
    private NomalAdapter mAdapter;


    public NomalRotationView(Context context) {
        super(context);
        mContext = context;
        View.inflate(mContext, R.layout.fragment_guide_view, this);
        initView();

    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.vp_guide);
        mPointLayout = (LinearLayout) findViewById(R.id.ll_point);
        mAdapter = new NomalAdapter();
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
    }

    public void setImageViewsData(List<Bitmap> bitmaps) {
        this.mBitmaps = bitmaps;
        if (mBitmaps.size() != 0) {
            mPoints.clear();
            mPointLayout.removeAllViews();
            for (int i = 0; i < bitmaps.size(); i++) {
                if (i == 0) {
                    initPointGroup(true);
                } else {
                    initPointGroup(false);
                }
            }
            mAdapter.notifyDataSetChanged();
            mPager.setCurrentItem(0);
        }
    }

    private void initPointGroup(boolean isSelected) {
        ImageView point = new ImageView(mContext);
        point.setImageResource(R.drawable.shape_point_selector);
        point.setSelected(isSelected);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = pointLeftMargin;
        point.setLayoutParams(params);
        mPointLayout.addView(point);
        mPoints.add(point);
    }

    private HeaderViewClickListener mHeaderViewClickListener;

    public void setHeaderViewClickListener(HeaderViewClickListener headerViewClickListener) {
        this.mHeaderViewClickListener = headerViewClickListener;
    }

    public interface HeaderViewClickListener {
        void HeaderViewClick(int position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mPoints.size(); i++) {
            mPoints.get(i).setSelected(false);
        }
        mPoints.get(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class NomalAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mBitmaps.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv;
            if (mImageViews.size() > 0) {
                iv = mImageViews.remove(0);
            } else {
                iv = new ImageView(mContext);
            }

            iv.setOnTouchListener(new OnTouchListener() {

                private int downX = 0;
                private long downTime = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            downX = (int) v.getX();
                            downTime = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_UP:
                            int moveX = (int) v.getX();
                            long moveTime = System.currentTimeMillis() - downTime;
                            if (downX == moveX && moveTime < 500) {
                                mHeaderViewClickListener.HeaderViewClick(position);
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                        default:
                            break;
                    }


                    return true;
                }
            });
            iv.setImageBitmap(mBitmaps.get(position));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object != null && object instanceof ImageView) {
                ImageView iv = (ImageView) object;
                container.removeView(iv);
                mImageViews.add(iv);
            }
            //            container.removeView(mImageViews.get(position));
        }
    }

}
