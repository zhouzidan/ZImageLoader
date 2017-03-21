package com.zhou.zimageloader;

import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 * 图片加载
 */

public class ZImageLoader {
    public static final String TAG = "ZImageLoader";
    private static ZImageLoader instance;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ZImageLoader() {
    }

    public static final ZImageLoader getInstance() {
        if (instance == null)
            instance = new ZImageLoader();
        return instance;
    }

    public void display(String imageUrl, ImageView imageView) {
        mExecutorService.submit(new DisplayImageRunnable(imageUrl, imageView));
    }

}
