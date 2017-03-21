package com.zhou.zimageloader.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ZImageLoaderSample
 * Created by zhou_guobao@lifang.com on 2017/3/20.
 * 存储
 */

public class StorageUtil {
    private static StorageUtil ourInstance = null;

    public static StorageUtil getInstance() {
        if (ourInstance == null)
            ourInstance = new StorageUtil();
        return ourInstance;
    }

    private StorageUtil() {
    }

    public File getRootDir(Context context) {
        File root = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            root = getCompleteDir(context, Environment.getExternalStorageDirectory());
        if (root == null || root.canWrite() == false)
            root = getCompleteDir(context, getOtherExtenalDir());
        if (root == null || root.canWrite() == false)
            root = getCompleteDir(context, getOtherExtenalDir());
        return root;
    }

    private File getCompleteDir(Context context, File root) {
        // 这个sd卡中文件路径下的内容会随着，程序卸载或者设置中清除缓存后一起清空
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache";
        File file = new File(root, cacheDir);
        if (file.exists() == false) {
            if (file.mkdirs() == false) {
                L.e("cannot create file's dir," + file.getPath());
                return null;
            }
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e) {
                L.e("cannot create nomedia file");
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 遍历 "system/etc/vold.fstab” 文件，获取全部的Android的挂载点信息
     *
     * @return
     */
    private File getOtherExtenalDir() {
        String[] toSearch = FileUtils.readFile("/etc/vold.fstab", "utf-8").toString().split(" ");
        List<String> devMountList = new ArrayList<String>();
        for (int i = 0; i < toSearch.length; i++) {
            if (toSearch[i].contains("dev_mount")) {
                if (new File(toSearch[i + 2]).exists()) {
                    devMountList.add(toSearch[i + 2]);
                }
            }
        }
        String path = null;

        for (String devMount : devMountList) {
            File file = new File(devMount);
            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();
                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                File testWritable = new File(path, "test_" + timeStamp);
                if (testWritable.mkdirs()) {
                    testWritable.delete();
                } else {
                    path = null;
                }
            }
        }

        if (path != null) {
            return new File(path);
        }
        return null;
    }


}
