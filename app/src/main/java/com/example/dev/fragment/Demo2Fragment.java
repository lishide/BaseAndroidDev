package com.example.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.adev.fragment.BaseFragment;
import com.example.dev.R;

public class Demo2Fragment extends BaseFragment {
    private static final String TAG = "Demo2Fragment";


    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_demo2, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initLogic(View view) {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

}
