package com.zhou.zimageloader.caching;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhou.zimageloader.AppInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ZImageLoader
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 */

public class DiskCacheMagager implements CacheInterface {
    private static DiskCacheMagager instance;

    public static DiskCacheMagager get() {
        if (instance == null) {
            instance = new DiskCacheMagager();
        }
        return instance;
    }

    private DiskCacheMagager() {
    }

    public void put(String url, Bitmap bitmap) {
        String fileName = AppInfo.get().getNameGenerator().generate(url);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(getDirPath() + File.separator + fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Bitmap get(String url) {
        String fileName = AppInfo.get().getNameGenerator().generate(url);
        return BitmapFactory.decodeFile(getDirPath() + File.separator + fileName);
    }

    public boolean has(String url) {
        String fileName = AppInfo.get().getNameGenerator().generate(url);
        File imgFile = new File(getDirPath() + File.separator + fileName);
        return imgFile.exists();
    }

    private String getDirPath() {
        return AppInfo.get().getStoragePath();
    }
}
