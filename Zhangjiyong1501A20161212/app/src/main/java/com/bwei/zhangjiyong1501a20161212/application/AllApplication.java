package com.bwei.zhangjiyong1501a20161212.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者： 南山君
 * 时间：2016/12/12.10:10
 */

public class AllApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this).build();

        ImageLoader.getInstance().init(configuration);
    }
}
