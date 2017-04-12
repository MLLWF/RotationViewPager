package com.mllwf.autorotationdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xutil.LogUtil.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/12.
 */

public class AutoRotationView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private final int DELAY_MILI = 3000;

    private Context mContext;
    private ViewPager mPager;
    private LinearLayout mPointLayout;
    private AutoAdapter mAdapter;

    private Handler mHandler;
    private RotationRunnable mAutoRunable;

    private List<Bitmap> mBitmaps = new ArrayList<>();
    private List<ImageView> mImageViews = new ArrayList<>();
    private List<ImageView> mPoints = new ArrayList<>();

    // TODO: 2017/4/12  界面样式参数
    private int pointLeftMargin = 20;


    public AutoRotationView(Context context) {
        super(context);
        mContext = context;
        View.inflate(mContext, R.layout.fragment_guide_view, this);
        initView();
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.vp_guide);
        mPointLayout = (LinearLayout) findViewById(R.id.ll_point);
        mHandler = new Handler();
        mAutoRunable = new RotationRunnable();
        mAdapter = new AutoAdapter();
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
        L.e("初始化控件和线程");
    }

    public AutoRotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
            mPager.setCurrentItem(bitmaps.size() + 10000);
            startRoll();
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

    public void startRoll() {
        mAutoRunable.start();
    }

    public void stopRoll() {
        mAutoRunable.stop();
    }


    /**
     * @param position             滑动页面的位置
     * @param positionOffset       滑动的百分比
     * @param positionOffsetPixels 滑动的像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private int prePosition = 0;

    /**
     * @param position 滑动结束之后选中的页面位置
     */
    @Override
    public void onPageSelected(int position) {
        L.e("onPageSelected->position  :" + position);
        mPoints.get(prePosition).setSelected(false);
        mPoints.get(position % mPoints.size()).setSelected(true);
        prePosition = position % mPoints.size();
        L.e("onPageSelected->prePosition  :" + prePosition);
    }

    /**
     * viewpager的状态
     *
     * @param state 0、1、2、
     *              0 ： 代表什么都没做
     *              1: 代表正在滑动
     *              2：代表滑动完毕
     */
    @Override
    public void onPageScrollStateChanged(int state) {


    }


    private class RotationRunnable implements Runnable {

        private boolean isRunnable = false;

        public void start() {

            if (!isRunnable) {
                isRunnable = true;
                //防止出现多线程滚动，取消上一次的滚动线程！
                mHandler.removeCallbacks(this);
                mHandler.postDelayed(this, DELAY_MILI);
            }
        }

        public void stop() {
            if (isRunnable) {
                isRunnable = true;
                mHandler.removeCallbacks(this);
            }
        }

        @Override
        public void run() {
            L.e("开始run。。。。。。。。。。。。");
            if (isRunnable) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                mHandler.postDelayed(this, DELAY_MILI);
            }
        }
    }

    private class AutoAdapter extends PagerAdapter {

        List<ImageView> imgViews = new ArrayList<>();

        @Override

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv;
            int realPosiion = position % mBitmaps.size();
            L.e("instantiateItem->realPosiion: " + realPosiion);
            if (imgViews.size() > 0) {
                iv = imgViews.remove(0);
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
                            mAutoRunable.stop();
                            downX = (int) v.getX();
                            downTime = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_UP:
                            mAutoRunable.start();
                            int moveX = (int) v.getX();
                            long moveTime = System.currentTimeMillis() - downTime;
                            if (downX == moveX && moveTime < 500) {
                                mHeaderViewClickListener.HeaderViewClick(position % mBitmaps.size());
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            mAutoRunable.start();
                            break;

                        default:
                            break;
                    }


                    return true;
                }
            });
            iv.setImageBitmap(mBitmaps.get(realPosiion));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object != null && object instanceof ImageView) {
                ImageView iv = (ImageView) object;
                container.removeView(iv);
                imgViews.add(iv);
            }
        }
    }

    private HeaderViewClickListener mHeaderViewClickListener;

    public void setHeaderViewClickListener(HeaderViewClickListener headerViewClickListener) {
        this.mHeaderViewClickListener = headerViewClickListener;
    }

    public interface HeaderViewClickListener {
        void HeaderViewClick(int position);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRoll();
    }
}