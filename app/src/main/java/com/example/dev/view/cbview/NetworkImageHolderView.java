package com.example.dev.view.cbview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.base.adev.R;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

/**
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        //设置加载中以及加载失败的图片
        Glide.with(context)
                .load(data)
                .placeholder(R.drawable.ic_default_adimage)
                .error(R.drawable.ic_default_adimage)
                .into(imageView);
    }
}
