package com.mllwf.nestedviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mllwf.nestedviewpager.adapter.OutPagerAadapter;
import com.mllwf.nestedviewpager.fragment.outlayer.OutFragmentFive;
import com.mllwf.nestedviewpager.fragment.outlayer.OutFragmentFour;
import com.mllwf.nestedviewpager.fragment.outlayer.OutFragmentOne;
import com.mllwf.nestedviewpager.fragment.outlayer.OutFragmentThree;
import com.mllwf.nestedviewpager.fragment.outlayer.OutFragmentTwo;
import com.mllwf.nestedviewpager.view.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoSlideViewPager mNoSlideViewPager;
    private RadioGroup mGroup;
    private RadioButton btn1, btn2, btn3, btn4, btn5;
    private List<Fragment> mFragmentList = new ArrayList<>();

    private OutPagerAadapter mAadapter;

    private Fragment outFmOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoSlideViewPager = (NoSlideViewPager) findViewById(R.id.noslideviewpager);
        mGroup = (RadioGroup) findViewById(R.id.rg);
        btn1 = (RadioButton) findViewById(R.id.rb1);
        btn2 = (RadioButton) findViewById(R.id.rb2);
        btn3 = (RadioButton) findViewById(R.id.rb3);
        btn4 = (RadioButton) findViewById(R.id.rb4);
        btn5 = (RadioButton) findViewById(R.id.rb5);

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    mNoSlideViewPager.setCurrentItem(0);
                } else if (checkedId == R.id.rb2) {
                    mNoSlideViewPager.setCurrentItem(1);
                } else if (checkedId == R.id.rb3) {
                    mNoSlideViewPager.setCurrentItem(2);
                } else if (checkedId == R.id.rb4) {
                    mNoSlideViewPager.setCurrentItem(3);
                } else if (checkedId == R.id.rb5) {
                    mNoSlideViewPager.setCurrentItem(4);
                }
            }
        });

        outFmOne = new OutFragmentOne();
        mFragmentList.add(outFmOne);
        mFragmentList.add(new OutFragmentTwo());
        mFragmentList.add(new OutFragmentThree());
        mFragmentList.add(new OutFragmentFour());
        mFragmentList.add(new OutFragmentFive());

        mAadapter = new OutPagerAadapter(getSupportFragmentManager(), mFragmentList);

        mNoSlideViewPager.setAdapter(mAadapter);
        mNoSlideViewPager.setOffscreenPageLimit(4);
    }
}
