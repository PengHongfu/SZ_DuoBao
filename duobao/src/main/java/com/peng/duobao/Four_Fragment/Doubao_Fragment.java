package com.peng.duobao.Four_Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.peng.duobao.Adapter.ImagePaperAdapter;
import com.peng.duobao.Goods_Fragement.Goods_four_Fragment;
import com.peng.duobao.Goods_Fragement.Goods_one_Fragment;
import com.peng.duobao.Goods_Fragement.Goods_three_Fragment;
import com.peng.duobao.Goods_Fragement.Goods_two_Fragment;
import com.peng.duobao.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Peng on 2016/8/7.
 */
public class Doubao_Fragment extends Fragment {

    private ViewPager mviewPager;
    /**
     * 用于小圆点图片
     */
    private List<ImageView> dotViewList;
    /**
     * 用于存放轮播效果图片
     */
    private List<String> list;

    private List<View> catesViewList;

    private LinearLayout dotLayout;//图片下方圆点的线性布局名称

    private int currentItem = 0;//当前页面

    boolean isAutoPlay = true;//是否自动轮播

    private ScheduledExecutorService scheduledExecutorService;

    public static String url1 = "http://dmmnm2aswnvy2.cloudfront.net/snatch/template/img/iphone6s.png";
    public static String url2 = "http://dmmnm2aswnvy2.cloudfront.net/snatch/template/img/iphonese.png";
    public static String url3 = "http://dmmnm2aswnvy2.cloudfront.net/snatch/template/img/mobilecard.png";

    private View view;//页面布局view

    private FrameLayout goodsfragment;
    private Goods_one_Fragment onefragment;
    private Goods_two_Fragment twofragment;
    private Goods_three_Fragment threefragment;
    private Goods_four_Fragment fourfragment;
    private RadioGroup goodsRG;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                mviewPager.setCurrentItem(currentItem);
            }
        }
    };
    private RadioGroup jing;
    private RadioGroup dong;
    private ScrollView sv;
    int[] location = new int[2];
    int[] location2 = new int[2];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doubao, null);
        /**
         * viewpager bander图片轮播布局
         * 图片底部圆点布局
         */
        mviewPager = (ViewPager) view.findViewById(R.id.myviewPager);
        dotLayout = (LinearLayout) view.findViewById(R.id.dotLayout);
        /**
         *  消除圆点布局
         */
        dotLayout.removeAllViews();

        initView();

        if (isAutoPlay) {
            startPlay();
        }

        goodsFragmentinitView();
        refresh();
        test();
        return view;
    }

    private void test() {
        dong = (RadioGroup) view.findViewById(R.id.goods_list_rg);
        jing = (RadioGroup) view.findViewById(R.id.goods_list_rg1);
        sv = (ScrollView) view.findViewById(R.id.scrollView);
        jing.setVisibility(View.GONE);

        sv.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;

            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    if (msg.what == touchEventId) {
                        if (lastY != sv.getScrollY()) {
                            //scrollview一直在滚动，会触发
                            handler.sendMessageDelayed(
                                    handler.obtainMessage(touchEventId, sv),5);
                            lastY = sv.getScrollY();
                            dong.getLocationOnScreen(location);
                            jing.getLocationOnScreen(location2);
                            //动的到静的位置时，静的显示。动的实际上还是网上滚动，但我们看到的是静止的那个
                            if (location[1] <= location2[1]) {
                                jing.setVisibility(View.VISIBLE);
                            } else {
                                //静止的隐藏了
                                jing.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            };
            public boolean onTouch(View v, MotionEvent event) {
                //必须两个都搞上，不然会有瑕疵。
                //没有这段，手指按住拖动的时候没有效果
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, v), 5);
                }
                //没有这段，手指松开scroll继续滚动的时候，没有效果
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }
        });
    }

    private void refresh() {
        final SwipeRefreshLayout refresh = (SwipeRefreshLayout) view.findViewById(R.id.sr_refresh);
        refresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                                Thread.currentThread().interrupt();
                        // 使用postInvalidate可以直接在线程中更新界面
                            view.postInvalidate();
                        }
                        refresh.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    /**
     * 商品tab 按钮的点击事件
     */
    private void goodsFragmentinitView() {

        goodsfragment = (FrameLayout) view.findViewById(R.id.doubao_goods_fragment);

        RadioButton rbone = (RadioButton) view.findViewById(R.id.rb_goods_one);
        RadioButton rbtwo = (RadioButton) view.findViewById(R.id.rb_goods_two);
        RadioButton rbthree = (RadioButton) view.findViewById(R.id.rb_goods_three);
        RadioButton rbfour = (RadioButton) view.findViewById(R.id.rb_goods_four);

        onefragment = new Goods_one_Fragment();
        /**
         * 加这个就不会加载这个布局 twofragment = new Goods_two_Fragment();
         */

        getChildFragmentManager().beginTransaction().replace(R.id.doubao_goods_fragment, onefragment).commit();

        /**
         * RadioGroup的点击事件
         */
        goodsRG = (RadioGroup) view.findViewById(R.id.goods_list_rg);
        goodsRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                FragmentManager goodsfragmentManager = getChildFragmentManager();
                FragmentTransaction beginTrasaction = goodsfragmentManager.beginTransaction();

                switch (checkedId) {
                    case R.id.rb_goods_one:
                        if (onefragment == null) {
                            onefragment = new Goods_one_Fragment();
                            beginTrasaction.add(R.id.doubao_goods_fragment, onefragment, "one");
                        }
                        if (twofragment != null)
                            beginTrasaction.hide(twofragment);
                        if (threefragment != null)
                            beginTrasaction.hide(threefragment);
                        if (fourfragment != null)
                            beginTrasaction.hide(fourfragment);

                        beginTrasaction.show(onefragment);
                        beginTrasaction.commit();

                        break;

                    case R.id.rb_goods_two:
                        if (twofragment == null) {
                            twofragment = new Goods_two_Fragment();
                            beginTrasaction.add(R.id.doubao_goods_fragment, twofragment, "two");
                            Log.d("peng1", "add----two");

                        }
                        if (onefragment != null)
                            beginTrasaction.hide(onefragment);
                        if (threefragment != null)
                            beginTrasaction.hide(threefragment);
                        if (fourfragment != null)
                            beginTrasaction.hide(fourfragment);

                        beginTrasaction.show(twofragment);
                        Log.d("peng1", twofragment.toString());
                        Log.d("peng1", "show----two");
                        beginTrasaction.commit();


                        break;
                    case R.id.rb_goods_three:
                        if (threefragment == null) {
                            threefragment = new Goods_three_Fragment();
                            beginTrasaction.add(R.id.doubao_goods_fragment, threefragment, "three");
                        }
                        if (twofragment != null)
                            beginTrasaction.hide(twofragment);
                        if (onefragment != null)
                            beginTrasaction.hide(onefragment);
                        if (fourfragment != null)
                            beginTrasaction.hide(fourfragment);

                        beginTrasaction.show(threefragment);
                        beginTrasaction.commit();

                        break;

                    case R.id.rb_goods_four:
                        if (fourfragment == null) {
                            fourfragment = new Goods_four_Fragment();
                            beginTrasaction.add(R.id.doubao_goods_fragment, fourfragment, "four");
                        }
                        if (twofragment != null)
                            beginTrasaction.hide(twofragment);
                        if (onefragment != null)
                            beginTrasaction.hide(onefragment);
                        if (threefragment != null)
                            beginTrasaction.hide(threefragment);
                        beginTrasaction.show(fourfragment);
                        beginTrasaction.commit();

                        break;

                    default:
                        break;
                }
            }
        });


    }

    public void initView() {
        /**
         * 用于小圆点图片
         * 用于存放轮播效果图片
         */
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<>();


        list.add(url1);
        list.add(url2);
        list.add(url3);

        for (int i = 0; i < list.size(); i++) {
            /**
             * context = etActivity().getApplicationContext()
             * 用了getBaseContext()会报
             * Invalid soft wrap cache entries emerged: Invalid soft wrap cache entries emerged
             */
            ImageView dotView = new ImageView(getActivity().getApplicationContext());
            /**
             * 圆点的显示布局<Todo>不是很清楚</Todo>
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;
            params.height = 20;//设置小圆点的大小
            params.width = 20;

            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.point_pressed);
            } else {

                dotView.setBackgroundResource(R.drawable.point_unpressed);
            }
            /**
             * addView(View child, ViewGroup.LayoutParams params)
             * Adds a child view with the specified layout parameters.将带有指定的布局参数的子视图添加
             * the child view to add子视图添加
             * the layout parameters to set on the child设置对孩子的布局参数
             */
            dotLayout.addView(dotView, params);
            /**
             *在列表的结尾添加指定的对象。
             * dotViewList用于小圆点图片
             */
            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }
        /**
         * 取到scroll_vew_item布局里的ImageView
         */
        catesViewList = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {

                View view = LayoutInflater.from(getActivity()).inflate(R.layout.duobao_bander, null);
                catesViewList.add(view);
            }
        }

        /**
         * 设置图片轮播适配器
         * catesViewList 存放图片显示布局的view
         *
         * list
         */
        ImagePaperAdapter adapter = new ImagePaperAdapter(getActivity(), catesViewList, list);

        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(0);
        mviewPager.addOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 4, 5, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }




    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentItem = (currentItem + 1) % list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        mviewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mviewPager.setCurrentItem(mviewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewList.get(pos)).setBackgroundResource(R.drawable.point_pressed);
                } else {
                    ((View) dotViewList.get(i)).setBackgroundResource(R.drawable.point_unpressed);
                }
            }
        }

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
    public void onAttach(Activity activity) {
        System.out.println("onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
