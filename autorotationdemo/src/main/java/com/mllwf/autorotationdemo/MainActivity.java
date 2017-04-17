package com.mllwf.autorotationdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.xutil.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] imgIds = new int[]{R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4};

    List<Bitmap> mBitmapList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < imgIds.length; i++) {
            mBitmapList.add(BitmapFactory.decodeResource(getResources(), imgIds[i]));
        }

        AutoRotationView view = new AutoRotationView(MainActivity.this);
        view.setImageViewsData(mBitmapList);
        view.setHeaderViewClickListener(new AutoRotationView.HeaderViewClickListener() {
            @Override
            public void HeaderViewClick(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dip2px(MainActivity.this, 450));
        setContentView(view,params);
    }
}
