package com.mllwf.nestedviewpager.fragment.innerlayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mllwf.nestedviewpager.R;
import com.mllwf.nestedviewpager.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/14.
 */

public class InnerFragmentOne extends Fragment {

    private View rootView;
    private RecyclerView mRecyclerView;
    private List<String> cellLists = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        rootView = inflater.inflate(R.layout.inner_fragment_one_view, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        mAdapter = new RecyclerAdapter(getActivity(), cellLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        return rootView;

    }

    private void initData() {
        for (int i = 0; i < 200; i++) {
            cellLists.add("item :" + i);
        }

    }
}
