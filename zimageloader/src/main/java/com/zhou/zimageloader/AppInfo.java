package com.zhou.zimageloader;

import android.content.Context;
import android.text.TextUtils;

import com.zhou.zimageloader.utils.StorageUtil;

import java.io.File;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/21.
 * 初始化需要的信息
 */

public class AppInfo {
    private static String packageName = null;//包名
    private static String storagePath = null;//存储路径

    public static String getPackageName() {
        return TextUtils.isEmpty(packageName) ? "" : packageName;
    }

    public static void setPackageName(Context context) {
        AppInfo.packageName = context.getPackageName();
    }

    public static String getStoragePath() {
        return TextUtils.isEmpty(storagePath) ? "" : storagePath;
    }

    public static void setStoragePath(String storagePath) {
        AppInfo.storagePath = storagePath;
    }

    //图片的存储路径
    public static void setStoragePath(Context context) {
        File rootFile = StorageUtil.getInstance().getRootDir(context);
        if (rootFile != null) {
            String storagePath = StorageUtil.getInstance().getRootDir(context).getPath();
            setStoragePath(storagePath);
        }
    }


}
