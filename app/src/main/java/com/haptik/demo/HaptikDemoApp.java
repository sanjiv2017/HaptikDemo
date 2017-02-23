package com.haptik.demo;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by sanjiv on 22/02/17.
 */

public class HaptikDemoApp extends Application {
    ImageLoaderConfiguration configuration ;

    @Override
    public void onCreate() {

        initLoader();

        super.onCreate();
    }


    private void initLoader(){
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .memoryCacheExtraOptions(200,200)
                .threadPoolSize(5) // default
                .memoryCache(new LruMemoryCache(50 * 1024 * 1024))
                .memoryCacheSize(50 * 1024 * 1024)
                //.diskCache(new UnlimitedDiskCache(cacheDir)) // default
                //.diskCacheSize(50 * 1024 * 1024)
                //.diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
