package com.base.adev.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.base.adev.R;
import com.base.adev.adapter.TabAdapter;

public abstract class BaseTabLayActivity extends BaseActivity {
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected TabAdapter mTabAdapter;

    @Override
    protected void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_base_tab);
        mViewPager = (ViewPager) findViewById(R.id.vp_base_pager);
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    protected void addFragment(Fragment fragment, String title) {
        mTabAdapter.addFragment(fragment, title);
        mTabAdapter.notifyDataSetChanged();
    }
}
