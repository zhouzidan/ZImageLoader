package com.zhou.zimageloader;

import android.content.Context;
import android.text.TextUtils;

import com.zhou.zimageloader.naming.FileNameGenerator;
import com.zhou.zimageloader.naming.HashCodeFileNameGenerator;
import com.zhou.zimageloader.utils.StorageUtil;

import java.io.File;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/21.
 * 初始化需要的信息
 */

public class AppInfo {
    private String packageName = null;//包名
    private String storagePath = null;//存储路径
    private FileNameGenerator mNameGenerator = null;//文件名加密

    private static AppInfo instance;

    private AppInfo() {
    }

    public static AppInfo get() {
        if (instance == null)
            instance = new AppInfo();
        return instance;
    }

    public String getPackageName() {
        return TextUtils.isEmpty(packageName) ? "" : packageName;
    }

    public void setPackageName(Context context) {
        this.packageName = context.getPackageName();
    }

    public String getStoragePath() {
        return TextUtils.isEmpty(storagePath) ? "" : storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    //图片的存储路径
    public void setStoragePath(Context context) {
        File rootFile = StorageUtil.getInstance().getRootDir(context);
        if (rootFile != null) {
            setStoragePath(rootFile.getPath());
        }
    }

    public FileNameGenerator getNameGenerator() {
        if (mNameGenerator == null)
            mNameGenerator = new HashCodeFileNameGenerator();
        return mNameGenerator;
    }

    public void setNameGenerator(FileNameGenerator nameGenerator) {
        mNameGenerator = nameGenerator;
    }
}
