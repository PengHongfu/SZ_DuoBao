package com.peng.duobao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
	public Object instantiateItem(ViewGroup container, int position) {
		View view =viewList.get(position);
		ViewParent viewParent =view.getParent();
		 ImageView imageView = (ImageView) view.findViewById(R.id.img);
		Glide.with(context).load(list.get(position)).into(imageView);

		(container).addView(viewList.get(position));
		return view;
	}
}