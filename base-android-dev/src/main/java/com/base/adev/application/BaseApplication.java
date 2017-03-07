package com.base.adev.application;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
