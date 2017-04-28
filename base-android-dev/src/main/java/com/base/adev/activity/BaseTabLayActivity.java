package com.base.adev.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.base.adev.R;
import com.base.adev.adapter.BaseFragmentStatePagerAdapter;

public abstract class BaseTabLayActivity extends BaseActivity {
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected BaseFragmentStatePagerAdapter mBaseFragmentStatePagerAdapter;

    @Override
    protected void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_base_tab);
        mViewPager = (ViewPager) findViewById(R.id.vp_base_pager);
        mBaseFragmentStatePagerAdapter = new BaseFragmentStatePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mBaseFragmentStatePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    protected void addFragment(Fragment fragment, String title) {
        mBaseFragmentStatePagerAdapter.addFragment(fragment, title);
        mBaseFragmentStatePagerAdapter.notifyDataSetChanged();
    }
}
