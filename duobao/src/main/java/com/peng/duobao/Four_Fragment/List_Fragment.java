package com.peng.duobao.Four_Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.peng.duobao.MainActivity;
import com.peng.duobao.R;

/**
 * Created by Peng on 2016/8/4.
 */
public class List_Fragment extends Fragment {
    private ViewPager mViewpager;
    private Button goduobao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_list,null);

        goduobao = (Button) view.findViewById(R.id.btn_goduobao);
        goduobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent();
                intent.setAction("List_Fragment.java");*/
                Intent intent = new Intent("Four_Fragment");
                intent.putExtra("peng","你好啊");
                /*getActivity().sendOrderedBroadcast(intent,null,null,null,1,"习大大给每个村民发了1000大米了",null);*/
                getActivity().sendOrderedBroadcast(intent,null);
                System.out.println("----------->>>发送了广播");
            }
        });
        return  view;
    }
}
