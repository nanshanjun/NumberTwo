package com.bwei.saner;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者： 南山君
 * 时间：2016/12/22.14:09
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader imageLoader;
        ImageLoader.getInstance().init(configuration);

    }
}
