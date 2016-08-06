package com.peng.duobao.main_fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peng.duobao.R;

/**
 * Created by Peng on 2016/8/4.
 */
public class Doubao_Fragment extends Fragment {
    //系统第一次画ui时调用
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_doubao,null);
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        System.out.println("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
    }
    //当在onCreateView里的view 初始化了
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("onDetach");
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        System.out.println("onAttach");
        super.onAttach(activity);
    }
}
