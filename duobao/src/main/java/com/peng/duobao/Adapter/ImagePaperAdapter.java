package com.peng.duobao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.peng.duobao.Activity_FirstFragment.TestActivity;
import com.peng.duobao.MainActivity;
import com.peng.duobao.R;

import java.util.List;

public class ImagePaperAdapter extends PagerAdapter {
    private Context context;
    private List<View> viewList;
    private List<String> list;

    public ImagePaperAdapter(Context context, List<View> viewList, List<String> list) {
        this.context = context;
        this.viewList = viewList;
        this.list = list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
        Glide.clear(viewList.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = viewList.get(position);
        ViewParent viewParent = view.getParent();
        ImageView imageView = (ImageView) view.findViewById(R.id.img);

        Glide.with(context).load(list.get(position)).into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.mainActivity,TestActivity.class);
                MainActivity.mainActivity.startActivity(intent);

                Toast.makeText(MainActivity.mainActivity,"你点击的是第"+position+"个页面",Toast.LENGTH_SHORT).show();
            }
        });


        (container).addView(viewList.get(position));
        return view;
    }
}