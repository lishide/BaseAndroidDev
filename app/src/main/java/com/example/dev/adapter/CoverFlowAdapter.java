package com.example.dev.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.adev.utils.MyBitmapImageViewTarget;
import com.bumptech.glide.Glide;
import com.example.dev.R;
import com.example.dev.bean.ChannelBean;
import com.img.coverflow.widget.ICoverFlowAdapter;

import java.util.List;

public class CoverFlowAdapter implements ICoverFlowAdapter {
    private List<ChannelBean> mArray;

    private Context context;

    public CoverFlowAdapter(Context context, List<ChannelBean> mArray) {
        this.context = context;
        this.mArray = mArray;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArray == null ? 0 : mArray.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.coverflow_item_view, null);
            holder.ivChannelImg = (ImageView) convertView.findViewById(R.id.iv_channelImg);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
        } else {
            holder = (Holder) convertView.getTag();
        }

        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void getData(View view, int position) {
        Holder holder = (Holder) view.getTag();

        ChannelBean channelBean = mArray.get(position);
        Glide.with(context)
                .load(channelBean.getImg())
                .asBitmap()
                .centerCrop()
                .into(new MyBitmapImageViewTarget(holder.ivChannelImg));

        holder.tv.setText(channelBean.getName());
    }

    public static class Holder {
        ImageView ivChannelImg;
        public TextView tv;
    }

}
