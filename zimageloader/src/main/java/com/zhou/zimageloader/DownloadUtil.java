package com.zhou.zimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/18.
 * 图片下载
 */

public class DownloadUtil {
    private static DownloadUtil instance;

    private DownloadUtil() {}

    public static final DownloadUtil getInstance() {
        if (instance == null)
            instance = new DownloadUtil();
        return instance;
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
