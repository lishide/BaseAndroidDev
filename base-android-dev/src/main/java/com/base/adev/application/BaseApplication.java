package com.base.adev.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BaseApplication extends Application {
    public static Context context;

    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue() {
        return queue;
    }


}
