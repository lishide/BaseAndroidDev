package com.example.dev.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dev.R;
import com.example.dev.bean.ListItemInfo;
import com.example.dev.listener.OnItemClickListener;

import java.util.List;

public class MainWidgetAdapter extends RecyclerView.Adapter<MainWidgetAdapter.DefaultViewHolder> {

    private List<ListItemInfo> mData;

    private OnItemClickListener mOnItemClickListener;

    public MainWidgetAdapter(List<ListItemInfo> data, OnItemClickListener onItemClickListener) {
        this.mData = data;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefaultViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mData.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvDescription;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_des);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(ListItemInfo listItemInfo) {
            this.tvTitle.setText(listItemInfo.getTitle());
            this.tvDescription.setText(listItemInfo.getSubTitle());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
