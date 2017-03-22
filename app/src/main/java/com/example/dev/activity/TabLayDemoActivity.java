package com.example.dev.activity;

import android.os.Bundle;

import com.base.adev.activity.BaseTabLayActivity;
import com.example.dev.R;
import com.example.dev.fragment.TabLayFragment;

public class TabLayDemoActivity extends BaseTabLayActivity {
    private String title;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_tab_lay_demo);
        // 把 BaseTabLayActivity 中的布局拿过来了，方便开发者修改布局样式和主题。
        // 直接在 activity_tab_lay_demo.xml 中修改即可，
        // 需要注意的是控件初始化操作（initView）已在 BaseTabLayActivity 类中完成，
        // 如果继承的是 BaseTabLayActivity，请保证控件 ID 一致。
        // 或者直接继承 BaseActivity 实现你的 TabLayout。
    }

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
