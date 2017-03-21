package com.zhou.zimageloader.utils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            if (context == null) {
                root = Environment.getExternalStoragePublicDirectory("ZImageLoader");
                if (root.exists() == false)
                    root.mkdirs();
            } else
                root = context.getExternalFilesDir(null);
        if (root == null || root.canWrite() == false)
            root = getCompleteDir(context, getOtherExtenalDir(context));
        if (root != null)
            addNoMediaFile(root);
        return root;
    }

    private File getCompleteDir(Context context, File root) {
        // 这个sd卡中文件路径下的内容会随着，程序卸载或者设置中清除缓存后一起清空
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/files";
        File file = new File(root, cacheDir);
        if (file.exists() == false) {
            if (file.mkdirs() == false) {
                L.e("cannot create file's dir," + file.getPath());
                return null;
            }
        }

        return file;
    }

    /**
     * 遍历 "system/etc/vold.fstab” 文件，获取全部的Android的挂载点信息
     *
     * @return
     */
    private File getOtherExtenalDir(Context context) {
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return new File(path);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    //创建一个noMedia文件，告诉媒体库，这里面的文件不被扫描
    private void addNoMediaFile(File file) {
        try {
            new File(file, ".nomedia").createNewFile();
        } catch (IOException e) {
            L.e("cannot create nomedia file");
            e.printStackTrace();
        }
    }

}
