package com.bwei.stethodemo.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * 作者： 南山君
 * 时间：2017/3/28.10:42
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
