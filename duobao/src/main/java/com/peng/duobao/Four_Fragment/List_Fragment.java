package com.peng.duobao.Four_Fragment;

import android.app.Activity;
import android.content.Context;
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
                getActivity().sendBroadcast(intent,null);
                System.out.println("----------->>>发送了广播");
            }
        });
        return  view;
    }
    @Override
    public void onAttach(Context context) {
        System.out.println("list--onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("list--onCreate");
        super.onCreate(savedInstanceState);
    }
    //当在onCreateView里的view 初始化了
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println("list--onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println("list--onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("list--onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("list--onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println("list--onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println("list--onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("list--onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("list--onDetach");
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        System.out.println("list--onAttach");
        super.onAttach(activity);
    }
}
