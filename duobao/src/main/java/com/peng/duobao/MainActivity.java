package com.peng.duobao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.peng.duobao.main_fragment.Discover_Fragment;
import com.peng.duobao.main_fragment.Doubao_Fragment;
import com.peng.duobao.main_fragment.List_Fragment;
import com.peng.duobao.main_fragment.Me_Fragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewpager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragmentViews = new ArrayList<>();

    private RadioGroup mainRg;

    private RadioButton mainDb;
    private RadioButton mainFx;
    private RadioButton mainQd;
    private RadioButton mainMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        rb_click();
        initEvent();


    }

    //滑动页面,底部的按钮一起更换图片
    private void initEvent() {

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                int cuttentItem = mViewpager.getCurrentItem();
                switch (cuttentItem) {
                    case 0:
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

    //点击按钮事件
    private void rb_click() {

        mainRg = (RadioGroup) findViewById(R.id.main_rg);

        mainDb = (RadioButton) findViewById(R.id.main_db);
        mainFx = (RadioButton) findViewById(R.id.main_fx);
        mainQd = (RadioButton) findViewById(R.id.main_qd);
        mainMe = (RadioButton) findViewById(R.id.main_me);

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
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

    //把布局加载到MainActivity中,形成滑动效果
    private void initView() {

        /**加载是个布局
         * 找到各种控件资源
         */
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        Fragment mtab01 = new Doubao_Fragment();
        Fragment mtab02 = new Discover_Fragment();
        Fragment mtab03 = new List_Fragment();
        Fragment mtab04 = new Me_Fragment();

        mFragmentViews.add(mtab01);
        mFragmentViews.add(mtab02);
        mFragmentViews.add(mtab03);
        mFragmentViews.add(mtab04);


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragmentViews.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentViews.size();
            }
        };
        mViewpager.setAdapter(mAdapter);

    }

}
