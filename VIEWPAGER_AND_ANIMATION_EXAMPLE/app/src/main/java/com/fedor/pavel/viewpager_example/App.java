package com.fedor.pavel.viewpager_example;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        configureImageLoader();

    }


    public void configureImageLoader(){

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY)
                .memoryCache(new LruMemoryCache(2))
                .memoryCacheSize(2)
                .diskCacheSize(50*1024*1024)
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(config);

    }
}
