package com.example.dev.activity;

import android.os.Bundle;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;

public class TextDemoActivity extends BaseActivity {

    private String title;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_text_demo);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {
        setTitle(title);

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }
}
