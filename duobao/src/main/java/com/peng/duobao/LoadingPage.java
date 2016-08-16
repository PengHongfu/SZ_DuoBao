package com.peng.duobao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Peng on 2016/8/15.
 */
public class LoadingPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingpage);


        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent mainIntent = new Intent(LoadingPage.this,MainActivity.class);
                LoadingPage.this.startActivity(mainIntent);//跳转到MainActivity
                LoadingPage.this.finish();
            }
        }, 3000);//给postDelayed()方法传递延迟参数
    }
}
