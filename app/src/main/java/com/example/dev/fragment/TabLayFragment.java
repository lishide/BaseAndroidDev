package com.example.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.adev.fragment.BaseFragment;
import com.example.dev.R;

public class TabLayFragment extends BaseFragment {

    //是否已经加载过数据
    private boolean isLoadData = false;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initLogic() {

        //如果没有加载过就加载，否则就不再加载了
        if (!isLoadData) {
            //加载数据操作

            isLoadData = true;
        }
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
