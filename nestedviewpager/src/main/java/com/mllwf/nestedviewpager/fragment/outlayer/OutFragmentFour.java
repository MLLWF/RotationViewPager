package com.mllwf.nestedviewpager.fragment.outlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mllwf.nestedviewpager.R;

/**
 * Created by ML on 2017/4/14.
 */

public class OutFragmentFour extends Fragment {

    private View rootView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.out_fragment_four_view, container, false);
        return rootView;
    }
}
