package com.bwie.myshops.app;

import android.app.Application;
import android.content.Context;

import com.bwie.myshops.utils.ImageLoaderUtils;

/**
 * Created by dell on 2017/2/10.
 */

public class MyApp extends Application {
    public static int bgNum = 0;
    private static Context context;
    public static boolean flag = false;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ImageLoaderUtils.initConfiguration(context);
    }
}
