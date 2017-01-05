package com.example.dev.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.base.adev.activity.BaseActivity;
import com.base.adev.utils.ScreenUtil;
import com.base.adev.view.MarqueeTextView;
import com.example.dev.R;

public class TextDemoActivity extends BaseActivity {

    private String title;
    private MarqueeTextView marqueeTextView;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_text_demo);

    }

    @Override
    protected void initView() {
        marqueeTextView = (MarqueeTextView) findViewById(R.id.tvScroll);

        int screenWidth = ScreenUtil.getScreenWidthPix(context);
        ViewGroup.MarginLayoutParams margin1 = new ViewGroup.MarginLayoutParams(
                marqueeTextView.getLayoutParams());
        margin1.setMargins(20, 0, 0, 0);//设置滚动区域位置：在左边距400像素，顶边距10像素的位置
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(margin1);
        layoutParams1.height = 158;//设置滚动区域高度
        layoutParams1.width = screenWidth; //设置滚动区域宽度

        marqueeTextView.setLayoutParams(layoutParams1);
        marqueeTextView.setScrollWidth(screenWidth);
        marqueeTextView.setCoordinateY(140);
        marqueeTextView.setCurrentPosition(screenWidth);//设置滚动信息从滚动区域的右边出来
        marqueeTextView.setSpeed(3);
    }

    @Override
    protected void initLogic() {
        setTitle(title);
        marqueeTextView.setText("我是滚动字幕啊12345，我是滚动字幕啊12345，我是滚动字幕啊12345");
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }
}
