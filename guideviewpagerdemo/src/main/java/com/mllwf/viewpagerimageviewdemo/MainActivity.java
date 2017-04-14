package com.mllwf.viewpagerimageviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xutil.ViewUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewUtils.bindClickListenerOnViews(this, this, R.id.btn_goto);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_goto) {
            Intent intent = new Intent(MainActivity.this, GuideActivity.class);
            startActivity(intent);
        }

    }
}
