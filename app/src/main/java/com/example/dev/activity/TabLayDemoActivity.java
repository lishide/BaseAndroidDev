package com.example.dev.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.base.adev.activity.BaseTabLayActivity;
import com.base.adev.adapter.TabAdapter;
import com.example.dev.R;
import com.example.dev.fragment.TabLayFragment;

public class TabLayDemoActivity extends BaseTabLayActivity {
    private String title;

    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected TabAdapter adapterTab;

    @Override
    protected void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

        adapterTab = new TabAdapter(getSupportFragmentManager());

        for (int i = 0; i < 3; i++) {
            TabLayFragment fragment = new TabLayFragment();
            addFragment(fragment, "TAB " + i);
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    protected void addFragment(Fragment fragment, String title) {
        adapterTab.addFragment(fragment, title);
        adapterTab.notifyDataSetChanged();
        viewPager.setAdapter(adapterTab);
        tabLayout.setupWithViewPager(viewPager);
    }
}
