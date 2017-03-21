package com.zhou.zimageloader.caching;

import android.graphics.Bitmap;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/21.
 * 缓存工具类的接口
 */

public interface CacheInterface {
    public void put(String url, Bitmap bitmap);

    public Bitmap get(String url);

    public boolean has(String url);
}
