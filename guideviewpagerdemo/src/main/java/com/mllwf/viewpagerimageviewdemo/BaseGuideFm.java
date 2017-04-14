package com.mllwf.viewpagerimageviewdemo;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.xutil.ConvertUtils;

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

    private Button btnOver;

    private ImageView whitePoint;
    private int mPointMargin;  //移动的距离


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_guide_view, container, false);
        initView();
        setBitmapDatas();
        initimgGroup(mBitmapList);
        mAdapter = new GuideAdapter(imgViews);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //TODO: 实现导航点平滑移动的方案
                // 页面滑动的时候，动态的获取小圆点的左边距
                int leftMargin = (int) (mPointMargin * (position + positionOffset));
                // 获取布局参数，然后设置布局参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) whitePoint.getLayoutParams();
                // 修改参数
                params.leftMargin = leftMargin;
                // 重新设置布局参数
                whitePoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //TODO: 实现导航点不平滑一定的方法
                //                for (int i = 0; i < llPoint.getChildCount(); i++) {
                //                    if (i == position) {
                //                        llPoint.getChildAt(i).setSelected(true);
                //                    } else {
                //                        llPoint.getChildAt(i).setSelected(false);
                //                    }
                //                }
                if (position == mBitmapList.size() - 1) {
                    btnOver.setVisibility(View.VISIBLE);
                } else {
                    btnOver.setVisibility(View.GONE);
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

    private void initView() {
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_guide);
        llPoint = (LinearLayout) rootView.findViewById(R.id.ll_point);
        btnOver = (Button) rootView.findViewById(R.id.btn_over);
        btnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "引导结束", Toast.LENGTH_SHORT).show();
            }
        });
        initWhitePoint();
    }

    private void initWhitePoint() {
        // TODO: 2017/4/14 初始化选中导航点，配置大小(这不能在绘制文件中指定大小，否则无法实现平滑移动的功能！)
        whitePoint = (ImageView) rootView.findViewById(R.id.white_point);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ConvertUtils.dip2px(getActivity(), 10), ConvertUtils.dip2px(getActivity(), 10));
        whitePoint.setLayoutParams(params);
        whitePoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            // TODO: 2017/4/14 设置监听事件，等视图界面绘制完成之后回调，算出点一点之间的距离
            @Override
            public void onGlobalLayout() {
                mPointMargin = llPoint.getChildAt(1).getLeft() - llPoint.getChildAt(0).getLeft();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    whitePoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
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
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ConvertUtils.dip2px(getActivity(), 10), ConvertUtils.dip2px(getActivity(), 10));
        if (i > 0) {
            parms.leftMargin = ConvertUtils.dip2px(getActivity(), 20);
        }
        point.setLayoutParams(parms);
        llPoint.addView(point);
    }

    //TODO: 配置引导页图片资源
    public abstract void setBitmapDatas();
}



