package com.peng.duobao.Four_Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.peng.duobao.MainActivity;
import com.peng.duobao.R;
import com.peng.duobao.WebView.Register_Web;
import com.peng.duobao.WebView.WebActivity;

/**
 * Created by Peng on 2016/8/4.
 */
public class Me_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_me,null);
        Button register = (Button) view.findViewById(R.id.bt_register);
        register.setOnClickListener(new Myclick());
        return  view;
    }

    /**
     * 注册点击事件
     */
     private class  Myclick implements View.OnClickListener{

         @Override
         public void onClick(View view) {
             Intent intent =new Intent(MainActivity.mainActivity,Register_Web.class);
             MainActivity.mainActivity.startActivity(intent);
         }
     }



}
