package com.example.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.base.adev.fragment.BaseFragment;
import com.example.dev.R;
import com.example.dev.activity.CoverFlowDemoActivity;
import com.example.dev.activity.TextDemoActivity;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";


    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        Button btnGotoDemo = (Button) view.findViewById(R.id.btn_gotoDemo);
        Button btnGotoCF = (Button) view.findViewById(R.id.btn_gotoCF);
        btnGotoDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "TextDemo");
                readyGo(TextDemoActivity.class, bundle);
            }
        });
        btnGotoCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "CoverFlowDemo");
                readyGo(CoverFlowDemoActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initLogic(View view) {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

}
