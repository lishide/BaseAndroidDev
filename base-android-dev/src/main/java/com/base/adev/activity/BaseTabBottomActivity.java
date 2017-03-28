package com.base.adev.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.base.adev.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部 Tab 的基础 Activity
 * 继承此 Activity 后，初始化布局、添加 addItem、initialise 两个方法，传入相关的图标及文字即可。
 * 想要更换 BottomNavigationBar 风格样式，请设置 setNavBarStyle 方法。
 */
public abstract class BaseTabBottomActivity extends BaseActivity
        implements BottomNavigationBar.OnTabSelectedListener {
    private FragmentManager mFragmentManager;
    private BottomNavigationBar mBottomNavigationBar;
    private final List<Fragment> fragmentList = new ArrayList<>();
    /**
     * BottomNavigationBar 的风格，默认：MODE_DEFAULT
     */
    private int mMode = BottomNavigationBar.MODE_DEFAULT;
    /**
     * BottomNavigationBar 的背景样式，默认：BACKGROUND_STYLE_DEFAULT
     */
    private int mBackgroundStyle = BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;

    @Override
    protected void initView() {
        mFragmentManager = this.getSupportFragmentManager();
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 添加 fragment 及 BottomNavigationItem（图标和文字）
     *
     * @param fragment    fragment
     * @param imageId     图标 ID
     * @param title       标题（String）
     * @param activeColor 选定时颜色
     */
    public void addItem(Fragment fragment, int imageId, String title, int activeColor) {
        fragmentList.add(fragment);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(imageId, title).setActiveColorResource(activeColor));
    }

    /**
     * 添加 fragment 及 BottomNavigationItem（图标和文字）
     *
     * @param fragment    fragment
     * @param imageId     图标 ID
     * @param title       标题（int）
     * @param activeColor 选定时颜色
     */
    public void addItem(Fragment fragment, int imageId, int title, int activeColor) {
        fragmentList.add(fragment);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(imageId, title).setActiveColorResource(activeColor));
    }

    /**
     * 添加 fragment 及 BottomNavigationItem（仅图标）
     *
     * @param fragment    fragment
     * @param imageId     图标 ID
     * @param activeColor 选定时颜色
     */
    public void addItem(Fragment fragment, int imageId, int activeColor) {
        fragmentList.add(fragment);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(imageId).setActiveColorResource(activeColor));
    }

    /**
     * 设置 BottomNavigationBar 风格样式
     *
     * @param mode            风格
     * @param backgroundStyle 背景样式
     */
    protected void setNavBarStyle(int mode, int backgroundStyle) {
        mMode = mode;
        mBackgroundStyle = backgroundStyle;
    }

    /**
     * 初始化容器，添加 fragment
     *
     * @param containerViewId 容器 ID
     */
    public void initialise(int containerViewId) {
        mBottomNavigationBar.setMode(mMode);
        mBottomNavigationBar.setBackgroundStyle(mBackgroundStyle);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(containerViewId, fragment);
        }
        transaction.commit();
        showFragment(0);
        mBottomNavigationBar.initialise();
    }

    /**
     * 显示 fragment
     *
     * @param position 位置
     */
    private void showFragment(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
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
        showFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
