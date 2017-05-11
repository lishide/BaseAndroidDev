package com.example.dev.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.dev.R;
import com.gongwen.marqueen.MarqueeFactory;

public class NoticeMF extends MarqueeFactory<TextView, String> {
    private LayoutInflater inflater;

    public NoticeMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TextView generateMarqueeItemView(String data) {
        TextView mView = (TextView) inflater.inflate(R.layout.item_notice_marquee, null);
        mView.setText(data);
        return mView;
    }
}