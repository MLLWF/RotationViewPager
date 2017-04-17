package com.mllwf.nestedviewpager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutil.ConvertUtils;
import com.mllwf.nestedviewpager.R;
import com.mllwf.nestedviewpager.view.AutoRotationView;
import com.mllwf.nestedviewpager.view.NomalRotationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private final int HEARD_VIEW = 1;
    private final int CELL_VIEW = 2;

    private Context mContext;
    private List<String> mList = new ArrayList<>();


    public RecyclerAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        for (int i = 0; i < imgIds.length; i++) {
            mBitmapList.add(BitmapFactory.decodeResource(mContext.getResources(), imgIds[i]));
        }
    }

    int[] imgIds = new int[]{R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};

    List<Bitmap> mBitmapList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEARD_VIEW) {
            FrameLayout view = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.cell_heard_view, parent, false);

            if (false) {
                // TODO: 2017/4/17 自动轮循
                AutoRotationView rotationView = new AutoRotationView(mContext);
                rotationView.setImageViewsData(mBitmapList);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dip2px(mContext, 200));
                view.addView(rotationView, params);
            } else {
                // TODO: 2017/4/17 手动滑动
                NomalRotationView rotationView = new NomalRotationView(mContext);
                rotationView.setImageViewsData(mBitmapList);
                rotationView.setHeaderViewClickListener(new NomalRotationView.HeaderViewClickListener() {
                    @Override
                    public void HeaderViewClick(int position) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dip2px(mContext, 200));
                view.addView(rotationView, params);
            }
            return new AutoHodler(view);
        }

        if (viewType == CELL_VIEW) {
            return new MyHodler(LayoutInflater.from(mContext).inflate(R.layout.cell_item_view, parent, false));
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHodler) {
            MyHodler h = (MyHodler) holder;
            h.mTextView.setText(mList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEARD_VIEW;
        } else {
            return CELL_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyHodler extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyHodler(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tvCell);
        }
    }

    private class AutoHodler extends RecyclerView.ViewHolder {
        public AutoHodler(View itemView) {
            super(itemView);
        }
    }
}
