package com.example.dev.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        // File cacheDir = context.getExternalCacheDir();//指定的是数据的缓存地址
        String diskCacheFolder = ConstUtil.APP_CACHE_PATH;
        int diskCacheSize = 1024 * 1024 * 30;//最多可以缓存多少字节的数据
        //设置磁盘缓存大小和位置
        builder.setDiskCache(new DiskLruCacheFactory(diskCacheFolder, "glide", diskCacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
