package com.example.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.adev.fragment.BaseFragment;
import com.example.dev.R;
import com.example.dev.activity.SettingActivity;
import com.example.dev.utils.MyGlideLoadUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MeFragment";
    private TextView tvMeSetting;
    private RelativeLayout rlHeadBg;
    private CircleImageView civUserHead;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        rlHeadBg = (RelativeLayout) view.findViewById(R.id.rl_head_bg);
        civUserHead = (CircleImageView) view.findViewById(R.id.civ_user_head);
        tvMeSetting = (TextView) view.findViewById(R.id.tv_me_setting);

    }

    @Override
    protected void initLogic(View view) {
        setCenterTitle(R.string.tab_me);
        tvMeSetting.setOnClickListener(this);
        MyGlideLoadUtil.loadCache(context, R.drawable.img_wk, civUserHead);

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_me_setting:
                bundle.putString("title", getString(R.string.txt_setting));
                readyGo(SettingActivity.class, bundle);
                break;
        }
    }
}
