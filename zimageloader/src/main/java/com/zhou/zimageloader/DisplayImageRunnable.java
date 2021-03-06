package com.zhou.zimageloader;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.zhou.zimageloader.caching.LruCacheMagager;
import com.zhou.zimageloader.utils.DownloadUtil;

public class DisplayImageRunnable implements Runnable {
    private String imageUrl;
    private ImageView mImageView;

    public DisplayImageRunnable(String imageUrl, ImageView imageView) {
        this.imageUrl = imageUrl;
        mImageView = imageView;
    }

    @Override
    public void run() {
        if (mImageView == null || TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Bitmap bitmap = null;
        bitmap = LruCacheMagager.get().get(imageUrl);
        //download
        if (bitmap == null) {
            bitmap = DownloadUtil.getInstance().downloadImage(imageUrl);
        }

        if (bitmap != null) {
            LruCacheMagager.get().put(imageUrl, bitmap);
            mImageView.post(new ShowImageRunnable(mImageView,bitmap));
        } else {
            Log.e("DisplayImageRunnable", "bitmap is null");
        }

    }


}