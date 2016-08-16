package com.peng.duobao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.peng.duobao.Four_Fragment.Discover_Fragment;
import com.peng.duobao.Four_Fragment.Doubao_Fragment;
import com.peng.duobao.Four_Fragment.List_Fragment;
import com.peng.duobao.Four_Fragment.Me_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public static MainActivity mainActivity;
    private FragmentPagerAdapter mAdapter;
    private FrameLayout mainFragment;
    private boolean isFirst = true;
    private boolean isInIndex = true;
    private long first, second;
    /**
     * 存放Fragment的集合
     */
    private List<Fragment> mFragmentViews = new ArrayList<>();

    private RadioGroup mainRg;

    private RadioButton mainDb;
    private RadioButton mainFx;
    private RadioButton mainQd;
    private RadioButton mainMe;
    private Broadreciver reciver;

    //四个fragment页面
    private Doubao_Fragment duobaofragment;
    private Discover_Fragment discoverFragment;
    private List_Fragment listFragment;
    private Me_Fragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        reciverguanbo();
        inintView();
    }

    private void reciverguanbo() {
        /**
         * 动态注册广播接收者
         * 静态要在清单文件里配置
         */
        reciver = new Broadreciver();
        IntentFilter filter = new IntentFilter("Four_Fragment");
        /**
         *  添加更多的响应的action
         *filter.addAction("jdfkljsl");
         */
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        /**
         * 销毁注册的广播
         */
        unregisterReceiver(reciver);
        super.onDestroy();

    }

    /**
     * 接收广播
     */
    public class Broadreciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             *  得到initialData发的数据
             String content = getResultData();
             */

            String message = intent.getStringExtra("peng");
            /**
             * 响应哪个action
             * 如果有多个action接收用 if (intent.getAction().equals("?"))
             */
            Log.d("duobao", "----->>>>接收到广播了,传过来的信息是:" + message);

            //获取fragment的管理者
            FragmentManager fragmentManager = getSupportFragmentManager();
            //开启事物
            FragmentTransaction beginTrasaction = fragmentManager.beginTransaction();
            if (duobaofragment == null) {
                duobaofragment = new Doubao_Fragment();
                beginTrasaction.add(R.id.main_fragment, duobaofragment);
            }
            if (discoverFragment != null)
                beginTrasaction.hide(discoverFragment);
            if (listFragment != null)
                beginTrasaction.hide(listFragment);
            if (meFragment != null)
                beginTrasaction.hide(meFragment);
            beginTrasaction.show(duobaofragment);
            beginTrasaction.commit();
        }
    }

    /**
     * 把四个布局加载到MainActivity中,形成滑动效果
     */
    //页面底部四个按钮的点击 显示不同的fragment
    private void inintView() {
        //底部四个按钮 夺宝 发现 清单 我的
        mainDb = (RadioButton) findViewById(R.id.main_db);
        mainFx = (RadioButton) findViewById(R.id.main_fx);
        mainQd = (RadioButton) findViewById(R.id.main_qd);
        mainMe = (RadioButton) findViewById(R.id.main_me);

        duobaofragment = new Doubao_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, duobaofragment).commit();

        //RadioGroup的点击事件 分别打开不同的fragment
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //获取fragment的管理者
                FragmentManager fragmentManager = getSupportFragmentManager();
                //开启事物
                FragmentTransaction beginTrasaction = fragmentManager.beginTransaction();

                switch (checkedId) {
                    case R.id.main_db:
                        if (duobaofragment == null) {
                            duobaofragment = new Doubao_Fragment();
                            beginTrasaction.add(R.id.main_fragment, duobaofragment);

                        }
                        if (discoverFragment != null)
                            beginTrasaction.hide(discoverFragment);
                        if (listFragment != null)
                            beginTrasaction.hide(listFragment);
                        if (meFragment != null)
                            beginTrasaction.hide(meFragment);
                        beginTrasaction.show(duobaofragment);
                        beginTrasaction.commit();
                        break;
                    case R.id.main_fx:
                        if (discoverFragment == null) {
                            discoverFragment = new Discover_Fragment();
                            beginTrasaction.add(R.id.main_fragment, discoverFragment);
                        }
                        if (duobaofragment != null)
                            beginTrasaction.hide(duobaofragment);
                        if (listFragment != null)
                            beginTrasaction.hide(listFragment);
                        if (meFragment != null)
                            beginTrasaction.hide(meFragment);
                        beginTrasaction.show(discoverFragment);
                        beginTrasaction.commit();
                        break;
                    case R.id.main_qd:
                        if (listFragment == null) {
                            listFragment = new List_Fragment();
                            beginTrasaction.add(R.id.main_fragment, listFragment);
                        }
                        if (duobaofragment != null)
                            beginTrasaction.hide(duobaofragment);
                        if (discoverFragment != null)
                            beginTrasaction.hide(discoverFragment);
                        if (meFragment != null)
                            beginTrasaction.hide(meFragment);
                        beginTrasaction.show(listFragment);
                        beginTrasaction.commit();
                        break;
                    case R.id.main_me:

                        if (meFragment == null) {
                            meFragment = new Me_Fragment();
                            beginTrasaction.add(R.id.main_fragment, meFragment);
                        }
                        if (duobaofragment != null)
                            beginTrasaction.hide(duobaofragment);
                        if (listFragment != null)
                            beginTrasaction.hide(listFragment);
                        if (discoverFragment != null)
                            beginTrasaction.hide(discoverFragment);
                        beginTrasaction.show(meFragment);
                        beginTrasaction.commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        onDoubleBackExist(keyCode, event);
        return true;
    }

    /**
     * 双击退出
     */
    private void onDoubleBackExist(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isFirst) {
                first = System.currentTimeMillis();
                Toast.makeText(this, "Click again to exit.", Toast.LENGTH_SHORT).show();
                isFirst = false;
            } else {
                isFirst = true;
                second = System.currentTimeMillis();
                if (second - first < 2000) {
                    finish();
                }
            }
        }
    }
}


