package com.example.dev.activity;

import android.os.Bundle;

import com.base.adev.activity.BaseTabBottomActivity;
import com.example.dev.R;
import com.example.dev.fragment.DemoFragment;
import com.example.dev.fragment.HomeFragment;
import com.example.dev.fragment.MeFragment;

public class MainActivity extends BaseTabBottomActivity {

    @Override
    protected void initView() {
        addFragment(new HomeFragment(), R.string.tab1, R.mipmap.ic_launcher);
        addFragment(new DemoFragment(), R.string.tab2, R.mipmap.ic_launcher);
        addFragment(new MeFragment(), R.string.tab_me, R.mipmap.ic_launcher);

        initialise();
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onTabSelected(int position) {
        super.onTabSelected(position);
    }

}
