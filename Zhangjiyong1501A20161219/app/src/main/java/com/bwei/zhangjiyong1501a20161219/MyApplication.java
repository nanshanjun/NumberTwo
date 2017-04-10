package com.bwei.zhangjiyong1501a20161219;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:00
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this).build();

        ImageLoader.getInstance().init(configuration);

    }
}
