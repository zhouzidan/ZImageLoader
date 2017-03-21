package com.zhou.zimageloadersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zhou.zimageloader.ZImageLoader;
import com.zhou.zimageloader.utils.L;
import com.zhou.zimageloader.utils.StorageUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.img_100);
        ZImageLoader.getInstance().display("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489834608605&di=ab7b4ad69225c5359d7fb1d8c4c30f5b&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F3b292df5e0fe992536be579530a85edf8cb17140.jpg"
                ,imageView);
        L.e("呵呵呵："+ StorageUtil.getInstance().getRootDir(this).getPath());
    }
}
