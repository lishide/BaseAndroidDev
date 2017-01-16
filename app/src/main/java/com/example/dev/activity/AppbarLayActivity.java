package com.example.dev.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.base.adev.activity.BaseActivity;
import com.base.adev.utils.StatusBarUtils;
import com.example.dev.R;

public class AppbarLayActivity extends BaseActivity {

    private static final String TAG = "AppbarLayActivity";
    private String title;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_appbar_lay);
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_lay_toolbar);
        setSupportActionBar(toolbar);

        StatusBarUtils.setTranslucentImageHeader(this, 0, toolbar);
        toolbar.setTitleTextColor(Color.TRANSPARENT);
        toolbar.setNavigationIcon(R.mipmap.navigation_back_white);
        // 设置navigation button 点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_layout);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        /**
         * AppBarLayout 的大小改变事件监听
         * 如下代码实现在 CollapsingToolbarLayout 完全收起时才显示标题
         *
         * 可不用实现，即当 CollapsingToolbarLayout 全屏没有折叠时，
         * title 显示的是大字体，在折叠的过程中，title 不断变小到一定大小的效果。
         * 根据需求合理调整
         */
        /*appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e(TAG, "appbarHeight:" + appBarLayout.getHeight()
                        + " getTotalScrollRange:" + appBarLayout.getTotalScrollRange()
                        + " offSet:" + verticalOffset);
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    toolbar.setTitleTextColor(Color.WHITE);
                    collapsingToolbarLayout.setTitle(title);
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });*/

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        fab2.setImageResource(R.drawable.ic_book_list);
        fab2.setCompatElevation(6);
    }

    @Override
    protected void initLogic() {
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab1, "点击 fab1", Snackbar.LENGTH_LONG).show();
            }
        });

        fab2.hide(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onHidden(FloatingActionButton fab) {
                Log.i(TAG, "fab hidden...");
            }
        });
        fab2.show(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                Log.i(TAG, "fab show...");
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab2, "你好，我是Snackbar，点击 fab 2", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

}
