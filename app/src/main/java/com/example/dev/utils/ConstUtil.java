package com.example.dev.utils;

import android.os.Environment;

public class ConstUtil {

    public static String SD_PATH = Environment.getExternalStorageDirectory().getPath();
    public static String APP_FILE_PATH = SD_PATH + "/BaseDevEg/";
    public static String APP_CACHE_PATH = APP_FILE_PATH + "/cache/";

}
