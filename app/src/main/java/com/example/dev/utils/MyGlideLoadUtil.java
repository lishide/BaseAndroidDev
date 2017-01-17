package com.example.dev.utils;

import android.content.Context;
import android.widget.ImageView;

import com.base.adev.utils.MyBitmapImageViewTarget;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Glide 加载图片
 */
public class MyGlideLoadUtil {

    /**
     * 简单加载图片(1)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void load(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .into(imageView);
    }

    /**
     * 简单加载图片(2)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void load(Context context, int picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .into(imageView);
    }

    /**
     * 加载图片并设置缓存(1)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void loadCache(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载图片并设置缓存(2)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void loadCache(Context context, int picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载图片并设置合适大小(1)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void loadWithCrop(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .asBitmap()
                .centerCrop()
                .into(new MyBitmapImageViewTarget(imageView));
    }

    /**
     * 加载图片并设置合适大小(2)
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void loadWithCrop(Context context, int picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .asBitmap()
                .centerCrop()
                .into(new MyBitmapImageViewTarget(imageView));
    }
}