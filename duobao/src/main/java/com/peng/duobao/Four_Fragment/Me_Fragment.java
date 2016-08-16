package com.peng.duobao.Four_Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.peng.duobao.MainActivity;
import com.peng.duobao.R;
import com.peng.duobao.WebView.Register_Web;
import com.peng.duobao.WebView.WebActivity;

/**
 * Created by Peng on 2016/8/4.
 */

public class Me_Fragment extends Fragment {
    private EditText etLoginUser;
    private EditText etLoginPwd;
    private TextView tvLoginForget;
    private Button btLogin;
    private Button btRegister;
    private CheckBox cbischeck;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_me,null);

        etLoginUser = (EditText) view.findViewById(R.id.et_login_user);
        etLoginPwd = (EditText) view.findViewById(R.id.et_login_pwd);
        tvLoginForget = (TextView) view.findViewById(R.id.tv_login_forget);
        btLogin = (Button) view.findViewById(R.id.bt_login);
        btRegister = (Button) view.findViewById(R.id.bt_register);
        cbischeck = (CheckBox) view.findViewById(R.id.cb_ischeck);

        btRegister.setOnClickListener(new MyRegister());
        btLogin.setOnClickListener(new MyLogin());
        cbischeck.setOnCheckedChangeListener(new Mycheck());
        //页面一开始显示的数据
        SharedPreferences sp = getActivity().getSharedPreferences("user",0);
        String name =sp.getString("name","");
        String pwd = sp.getString("pwd","");
        etLoginUser.setText(name);
        etLoginPwd.setText(pwd);
        Boolean  ischeckd=sp.getBoolean("ischeck",false);
        if(ischeckd){
            cbischeck.setChecked(ischeckd);
        }
        return  view;
    }
    private  class Mycheck implements CompoundButton.OnCheckedChangeListener {
        // 检查复选框是否被选中
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            SharedPreferences sp = getActivity().getSharedPreferences("user",0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("ischeck",b);//b ischeckd ture or false
            editor.commit();
            System.out.println("----->>>>>check"+b);
        }
    }

    private class  MyLogin implements View.OnClickListener {
        //点击登陆按钮
        @Override
        public void onClick(View view) {
            String name = etLoginUser.getText().toString().trim();
            String pwd = etLoginPwd.getText().toString().trim();

            if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)) {
                Toast.makeText(getActivity(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            }else {
                if(cbischeck.isChecked()){
                    SharedPreferences sp = getActivity().getSharedPreferences("user",0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name",name);
                    editor.putString("pwd",pwd);
                    editor.commit();
                    Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(), "请勾选记住密码", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    /**
     * 注册点击事件
     */
     private class  MyRegister implements View.OnClickListener {

         @Override
         public void onClick(View view) {
             Intent intent =new Intent(MainActivity.mainActivity,Register_Web.class);
             MainActivity.mainActivity.startActivity(intent);
         }
     }



}
