package com.mllwf.viewpagerimageviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ML on 2017/4/14.
 */

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new GuideFragment()).commit();
    }
}
