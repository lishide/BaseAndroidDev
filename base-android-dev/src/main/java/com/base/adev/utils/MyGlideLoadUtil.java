package com.base.adev.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MyGlideLoadUtil {

    /**
     * 简单加载图片
     */
    public static void load(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .into(imageView);
    }

    /**
     * 加载图片并设置缓存
     */
    public static void loadCache(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载图片并设置合适大小
     */
    public static void loadWithCrop(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .asBitmap()
                .centerCrop()
                .into(new MyBitmapImageViewTarget(imageView));
    }
}