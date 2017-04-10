package com.bwei.zhangjiyong1502b20170305a;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者： 南山君
 * 时间：2017/3/5.14:13
 */

public class Myapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this).build();

        ImageLoader.getInstance().init(configuration);


    }
}
