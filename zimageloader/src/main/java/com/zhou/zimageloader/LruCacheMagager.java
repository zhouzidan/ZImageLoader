package com.zhou.zimageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * ZImageLoader
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 * LRU是Least Recently Used 近期最少使用算法  缓存管理
 */

public class LruCacheMagager {
    private static LruCacheMagager instance;
    LruCache<String, Bitmap> mBitmapLruCache;

    public static LruCacheMagager get() {
        if (instance == null) {
            instance = new LruCacheMagager();
        }
        return instance;
    }

    private LruCacheMagager() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 4);
        mBitmapLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void put(String key, Bitmap bitmap) {
        if (has(key) == false)
            mBitmapLruCache.put(key, bitmap);
    }

    public Bitmap get(String key) {
        return mBitmapLruCache.get(key);
    }

    public boolean has(String key) {
        return mBitmapLruCache.get(key) != null;
    }


}
