package com.example.dev.activity;

import android.os.Bundle;

import com.base.adev.activity.BaseTabLayActivity;
import com.example.dev.fragment.TabLayFragment;

public class TabLayDemoActivity extends BaseTabLayActivity {
    private String title;

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

        for (int i = 0; i < 3; i++) {
            TabLayFragment fragment = new TabLayFragment();
            addFragment(fragment, "TAB " + i);
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }
}
