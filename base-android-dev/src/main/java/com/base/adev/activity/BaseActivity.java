package com.base.adev.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.adev.R;
import com.base.adev.utils.SystemBarTintManager;
import com.base.adev.utils.ToastUtils;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public BaseActivity() { /* compiled code */ }

    protected BaseActivity activity;
    protected View rootView;

    public boolean useTitle = true;

    public Toolbar mToolbar;
    public TextView title;
    public ImageView back;
    public TextView tv_menu;
    public ImageView iv_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//通知栏所需颜色
        }

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        activity = this;
        initContentView(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initTitle();
        }

        initView();
        initLogic();
    }

    protected abstract void initContentView(Bundle bundle);

    protected abstract void initView();

    protected abstract void initLogic();

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected void initTitle() {
        title = (TextView) findViewById(R.id.toolbar_title);
        back = (ImageView) findViewById(R.id.toolbar_back);
        iv_menu = (ImageView) findViewById(R.id.toolbar_iv_menu);
        tv_menu = (TextView) findViewById(R.id.toolbar_tv_menu);
        if (null != back) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void setTitle(String string) {
        title.setText(string);
    }

    public void setTitle(int id) {
        title.setText(id);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void chooseUseTitle(boolean choose) {
        useTitle = choose;
    }

    //Toast显示
    protected void showShortToast(String string) {
        ToastUtils.showShortToast(this, string);
    }

    protected void showShortToast(int stringId) {
        ToastUtils.showShortToast(this, stringId);
    }

    protected void showLongToast(String string) {
        ToastUtils.showShortToast(this, string);
    }

    protected void showLongToast(int stringId) {
        ToastUtils.showShortToast(this, stringId);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 界面跳转
     *
     * @param cls 目标Activity
     */
    protected void readyGo(Class<?> cls) {
        readyGo(cls, null);
    }

    /**
     * 跳转界面，传参
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param cls 目标Activity
     */
    protected void readyGoThenKill(Class<?> cls) {
        readyGoThenKill(cls, null);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        readyGo(cls, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}