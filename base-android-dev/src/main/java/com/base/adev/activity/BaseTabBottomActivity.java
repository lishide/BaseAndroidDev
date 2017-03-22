package com.base.adev.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.base.adev.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTabBottomActivity extends BaseActivity {

    private BottomBarUtil bottomBarUtil;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.base_activity_tab_bottom);
        bottomBarUtil = new BottomBarUtil(this, R.id.llRoot, R.id.bottom_bar);
    }

    protected void addFragment(Fragment fragment, String title, int imgId) {
        bottomBarUtil.addItem(fragment, title, imgId, R.color.colorPrimary);
    }

    protected void addFragment(Fragment fragment, int titleId, int imgId) {
        bottomBarUtil.addItem(fragment, getResources().getString(titleId), imgId, R.color.colorPrimary);
    }

    protected void onTabSelected(int position) {

    }

    protected void initialise() {
        bottomBarUtil.initialise();
    }

    class BottomBarUtil implements BottomNavigationBar.OnTabSelectedListener {

        private int rootViewId;
        private FragmentManager fragmentManager;
        private BottomNavigationBar navigationBar;
        private final List<Fragment> fragmentList = new ArrayList<>();

        public BottomBarUtil(AppCompatActivity activity, int rootViewId, int barId) {
            this.rootViewId = rootViewId;

            fragmentManager = activity.getSupportFragmentManager();
            navigationBar = (BottomNavigationBar) activity.findViewById(barId);
            navigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        }

        public void addItem(Fragment fragment, String title, Integer imageId, int color) {
            fragmentList.add(fragment);
            navigationBar.addItem(new BottomNavigationItem(imageId, title).setInActiveColor(color));
        }

        public void initialise() {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (Fragment fragment : fragmentList) {
                transaction.add(rootViewId, fragment);
            }
            transaction.commit();
            showFragment(0);
            navigationBar.initialise();
            navigationBar.setTabSelectedListener(this);
        }

        private void showFragment(int position) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i != position) {
                    transaction.hide(fragmentList.get(i));
                } else {
                    transaction.show(fragmentList.get(i));
                }
            }
            transaction.commit();
        }

        @Override
        public void onTabSelected(int position) {
            navigationBar.selectTab(position);
            showFragment(position);
            BaseTabBottomActivity.this.onTabSelected(position);
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    }
}
