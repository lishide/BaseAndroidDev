package com.example.dev.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.hpplay.callback.HpplayWindowPlayCallBack;
import com.hpplay.link.HpplayLinkControl;

/**
 * 乐播投屏 Demo
 */
public class HpplayActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "HpplayActivity";
    private String title;

    private EditText mPlayUrlEdit;

    private HpplayLinkControl mHpplayLinkControl;

    private String mPlayUrl;

    /**
     * 官网申请到的Key
     */
    private static final String mKey = "51418c8431bdc242e2e74f001cd2ad0b";

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_hpplay);
    }

    @Override
    protected void initView() {
        mHpplayLinkControl = HpplayLinkControl.getInstance();
        mHpplayLinkControl.setDebug(true);
        mHpplayLinkControl.initHpplayLink(this, mKey);

        mPlayUrlEdit = (EditText) findViewById(R.id.et_hpplay_play);
        findViewById(R.id.hpplay_mirror).setOnClickListener(this);
        findViewById(R.id.hpplay_push).setOnClickListener(this);
        findViewById(R.id.hpplay_all).setOnClickListener(this);
        findViewById(R.id.btn_hpplay_close_mirr).setOnClickListener(this);
        findViewById(R.id.btn_hpplay_close_connect).setOnClickListener(this);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    /**
     * 镜像功能回调用，必须调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mHpplayLinkControl.startMirrorResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hpplay_mirror:
                mHpplayLinkControl.showHpplayWindow(context, null);
                break;
            case R.id.hpplay_push:
                mPlayUrl = mPlayUrlEdit.getText().toString();
                if (TextUtils.isEmpty(mPlayUrl)) {
                    mPlayUrl = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
                }
                mHpplayLinkControl.showHpplayWindow(context, mPlayUrl, 0,
                        new HpplayWindowPlayCallBack() {
                            @Override
                            public void onIsConnect(boolean b) {
                                Log.d(TAG, "是否成功连接到电视 " + b);
                            }

                            @Override
                            public void onIsPlaySuccess(boolean b) {
                                Log.d(TAG, "是否成功推送地址到电视 " + b);
                            }
                        });
                break;
            case R.id.hpplay_all:
                mPlayUrl = mPlayUrlEdit.getText().toString();
                if (TextUtils.isEmpty(mPlayUrl)) {
                    mPlayUrl = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
                }
                mHpplayLinkControl.showHpplayWindow(context, mPlayUrl,
                        new HpplayWindowPlayCallBack() {
                            @Override
                            public void onIsConnect(boolean b) {
                                Log.d(TAG, "是否成功连接到电视 " + b);
                            }

                            @Override
                            public void onIsPlaySuccess(boolean b) {
                                Log.d(TAG, "是否成功推送地址到电视 " + b);
                            }
                        });
                break;
            case R.id.btn_hpplay_close_mirr:
                if (mHpplayLinkControl.getMirrState()) {
                    showShortToast("镜像中，正在关闭");
                    mHpplayLinkControl.closeHpplayLink();
                } else {
                    showShortToast("未镜像");
                }
                break;
            case R.id.btn_hpplay_close_connect:
                if (mHpplayLinkControl.isConnect()) {
                    showShortToast("连接中，正在关闭");
                    mHpplayLinkControl.closeHpplayLink();
                } else {
                    showShortToast("未连接");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mHpplayLinkControl.dismissHpplayWindow();

    }
}
