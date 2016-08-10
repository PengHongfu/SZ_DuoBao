package com.peng.duobao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
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
    private ViewPager mViewpager;
    private FragmentPagerAdapter mAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        initView();
        rb_click();
        initEvent();

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

            mViewpager.setCurrentItem(0);
            System.out.println("----->>>>接收到广播了,传过来的信息是:" + message);
        }
    }

    /**
     * 把四个布局加载到MainActivity中,形成滑动效果
     */
    private void initView() {

        /**加载四个布局
         * 找到各种控件资源
         */
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        /**
         * 得到Fragment对象
         */
        Fragment mtab01 = new Doubao_Fragment();
        Fragment mtab02 = new Discover_Fragment();
        Fragment mtab03 = new List_Fragment();
        Fragment mtab04 = new Me_Fragment();

        /**
         * 把得到的Fragment对象 加到List<Fragment> 集合当中
         */
        mFragmentViews.add(mtab01);
        mFragmentViews.add(mtab02);
        mFragmentViews.add(mtab03);
        mFragmentViews.add(mtab04);

        /**
         * 适配器,通过new FragmentPagerAdapter 对象的方法 下面的注释是另一种方法
         * 把得到的fragment 通过适配器 ,显示在viewpager上
         * mViewpager.setAdapter(mAdapter);
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            /**
             * 获得相应数据集合中特定位置的数据项
             */
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragmentViews.get(position);
            }

            /**
             *Fragment的数量大小
             */
            @Override
            public int getCount() {
                return mFragmentViews.size();
            }
        };
        mViewpager.setAdapter(mAdapter);

        /* 适配器,通过继承FragmentPagerAdapter的方法
        onCreate{...
        mViewpager.setAdapter(new myadapter(getSupportFragmentManager()));
        ..}
        //在oncreate方法外定义
    private  class myadapter extends FragmentPagerAdapter{

        public myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentViews.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentViews.size();
        }
    }*/
    }

    /**
     * Viewpager滑动响应事件,底部的按钮更换图片
     */
    private void initEvent() {
        /**
         *Viewpager滑动的监听事件
         */
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int cuttentItem = mViewpager.getCurrentItem();
                switch (cuttentItem) {
                    case 0:
                        /**
                         * 四个Radiobutton的check事件
                         * 图片在selected_ic_home定义选中和未选择事件
                         * android:drawableTop="@drawable/selected_ic_home"
                         * android:checked="true"表示选中
                         */
                        mainDb.setChecked(true);
                        break;
                    case 1:
                        mainFx.setChecked(true);
                        break;
                    case 2:
                        mainQd.setChecked(true);
                        break;
                    case 3:
                        mainMe.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 底部四个按钮的点击事件
     */
    private void rb_click() {
        /**
         * RadioGroup里包含4个RadioButton
         * 通过RadioGroup的setOnCheckedChangeListener方法检测点击的按钮
         */
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        /**
         * 取到4个RadioButton按钮
         */
        mainDb = (RadioButton) findViewById(R.id.main_db);
        mainFx = (RadioButton) findViewById(R.id.main_fx);
        mainQd = (RadioButton) findViewById(R.id.main_qd);
        mainMe = (RadioButton) findViewById(R.id.main_me);
        /**
         * 监听事件
         */
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                /**
                 * 默认从0开始
                 * 按照上面定义4个按钮的方法顺序从0开始
                 */
                switch (checkedId) {
                    case R.id.main_db:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.main_fx:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.main_qd:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.main_me:
                        mViewpager.setCurrentItem(3);
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


