package com.peng.duobao.Activity_FirstFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.peng.duobao.R;

/**
 * Created by Peng on 2016/8/7.
 */
public class TestActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstfragment);
        ImageView imageView = (ImageView) findViewById(R.id.iv_image);

        Glide.with(TestActivity.this).load("http://dmmnm2aswnvy2.cloudfront." +
                "net/snatch/template/img/iphonese.png").into(imageView);
        //Glide.with(this).load("").into(imageView2);
    }
}
