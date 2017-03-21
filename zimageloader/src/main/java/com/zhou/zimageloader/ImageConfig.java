package com.zhou.zimageloader;

import android.content.Context;
import android.text.TextUtils;

import com.zhou.zimageloader.naming.FileNameGenerator;

public class ImageConfig {
    private Context mContext;
    private String mStoragePath;
    private FileNameGenerator mFileNameGenerator;

    public ImageConfig(Context context) {
        mContext = context;
    }

    public void setStoragePath(String storagePath) {
        mStoragePath = storagePath;
    }

    public void build() {
        AppInfo.get().setPackageName(mContext);
        if (TextUtils.isEmpty(mStoragePath))
            AppInfo.get().setStoragePath(mContext);
        else
            AppInfo.get().setStoragePath(mStoragePath);
        AppInfo.get().setNameGenerator(mFileNameGenerator);
    }
}