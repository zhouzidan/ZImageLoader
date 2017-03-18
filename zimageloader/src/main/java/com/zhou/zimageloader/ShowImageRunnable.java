package com.zhou.zimageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 * TODO 描述
 */

public class ShowImageRunnable implements Runnable {
    private ImageView mImageView;
    private Bitmap mBitmap;

    public ShowImageRunnable(ImageView imageView, Bitmap bitmap) {
        mImageView = imageView;
        mBitmap = bitmap;
    }

    @Override
    public void run() {
        mImageView.setImageBitmap(mBitmap);
    }
}
