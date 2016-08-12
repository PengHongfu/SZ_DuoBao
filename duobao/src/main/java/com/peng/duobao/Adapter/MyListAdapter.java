package com.peng.duobao.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peng.duobao.R;

/**
 * Created by Peng on 2016/8/12.
 */
public class MyListAdapter extends BaseAdapter {
    public final static String www="http://dmmnm2aswnvy2.cloudfront.net/snatch/template/images/product/";
    private String url[]={
            www+"iphonese/iphonese.jpg?v=2",
            www+"iphone6s/iphone6s.jpg?v=2",
            www+"lenovo/top.jpg?v=2",
            www+"lgtv/top.jpg",
            www+"asus/top.jpg"};
    private int progress[] ={62,93,45,66,45};
    private Context mContext;

    public MyListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.goods_two_item, null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_goods_name);
            viewHolder.progress = (ProgressBar) convertView.findViewById(R.id.tv_goods_progress);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            viewHolder.buycar = (ImageButton) convertView.findViewById(R.id.ib_goods_add);
            viewHolder.buy = (Button) convertView.findViewById(R.id.bt_goods_buy);

            convertView.setTag(viewHolder);
        }
        {
            viewHolder = (ViewHolder) convertView.getTag();

            //设置ProgressBar为可见状态
            viewHolder.progress.setVisibility(View.VISIBLE);

            //设置ProgressBar的最大值
            viewHolder.progress.setMax(100);
            //设置ProgressBar当前值
            viewHolder.progress.setProgress(progress[position]);
            viewHolder.price.setText("1000coin");
            Glide.with(mContext).load(url[position]).into(viewHolder.image);
        }

        return convertView;
    }

    public final class ViewHolder {
        public ImageView image;
        public TextView title;
        public ProgressBar progress;
        public TextView price;
        public ImageButton buycar;
        public Button buy;
    }
}