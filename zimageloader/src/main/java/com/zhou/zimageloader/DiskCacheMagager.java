package com.zhou.zimageloader;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.zhou.zimageloader.utils.StorageUtil;

import java.io.File;

/**
 * ZImageLoader
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 */

public class DiskCacheMagager {
    private static DiskCacheMagager instance;
    private String dirPath = null;

    public static DiskCacheMagager get() {
        if (instance == null) {
            instance = new DiskCacheMagager();
        }
        return instance;
    }

    private DiskCacheMagager() {
    }

    public void put(String key, Bitmap bitmap) {

    }

    public Bitmap get(String key) {
        return null;
    }

    public boolean has(String key) {
        return false;
    }

    private String getDirPath() {
        if (TextUtils.isEmpty(dirPath))
            dirPath = StorageUtil.getInstance().getRootDir(null).getPath() + File.separatorChar;
        return dirPath;
    }
}
