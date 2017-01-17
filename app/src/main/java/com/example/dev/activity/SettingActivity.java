package com.example.dev.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.adev.activity.BaseActivity;
import com.base.adev.utils.DataCleanManager;
import com.base.adev.utils.GetFolderSizeManager;
import com.example.dev.R;
import com.example.dev.application.MyApplication;
import com.example.dev.utils.ConstUtil;

import java.io.File;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private String title;
    private TextView tvAppVersion;
    private TextView tvCache;
    private LinearLayout layClearCache;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        layClearCache = (LinearLayout) findViewById(R.id.lay_clear_cache);
        layClearCache.setOnClickListener(this);
        tvCache = (TextView) findViewById(R.id.tv_cache);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        tvAppVersion.setText(MyApplication.getVersion());

        //缓存
        File cachePath = new File(ConstUtil.APP_FILE_PATH);
        tvCache.setText(getCacheSize(cachePath));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    private String getCacheSize(File cachePath) {
        String cacheFormatSize;
        if (cachePath.exists()) {
            long cacheSize = GetFolderSizeManager.getFolderSize(cachePath);
            cacheFormatSize = GetFolderSizeManager.getFormatSize(cacheSize);
        } else {
            cacheFormatSize = "0.0Byte(s)";
        }
        return cacheFormatSize;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_clear_cache:
                dlgConfirmCC();
                break;
        }
    }

    private void dlgConfirmCC() {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否清空缓存？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCleanManager.cleanCustomCache(ConstUtil.APP_FILE_PATH);
                        showShortToast("缓存已清理");
                        tvCache.setText("0KB");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
