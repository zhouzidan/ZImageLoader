package com.zhou.zimageloader;

import android.content.Context;
import android.text.TextUtils;

public class ImageConfig {
    private Context mContext;
    private String mStoragePath;

    public ImageConfig(Context context) {
        mContext = context;
    }

    public void setStoragePath(String storagePath) {
        mStoragePath = storagePath;
    }

    public void build() {
        AppInfo.setPackageName(mContext);
        if (TextUtils.isEmpty(mStoragePath))
            AppInfo.setStoragePath(mContext);
        else
            AppInfo.setStoragePath(mStoragePath);
    }
}