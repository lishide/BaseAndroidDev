package com.example.dev.activity;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.base.adev.activity.BaseTabBottomActivity;
import com.example.dev.R;
import com.example.dev.fragment.DemoFragment;
import com.example.dev.fragment.HomeFragment;
import com.example.dev.fragment.MeFragment;

public class MainActivity extends BaseTabBottomActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_base_tab_bottom);
    }

    @Override
    protected void initLogic() {
        addItem(new HomeFragment(), R.drawable.ic_tab_home_white_24dp, R.string.tab1, R.color.colorPrimary);
        addItem(new DemoFragment(), R.drawable.ic_tab_book_white_24dp, R.string.tab2, R.color.colorPrimaryDark);
        addItem(new MeFragment(), R.drawable.ic_tab_tv_white_24dp, R.string.tab_me, R.color.colorAccent);

        setNavBarStyle(BottomNavigationBar.MODE_FIXED, BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        initialise(R.id.ll_content);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onTabSelected(int position) {
        super.onTabSelected(position);
    }

}
