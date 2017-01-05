package com.example.dev.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dev.R;
import com.example.dev.listener.OnItemClickListener;

import java.util.List;

public class MainWidgetAdapter extends RecyclerView.Adapter<MainWidgetAdapter.DefaultViewHolder> {

    private List<String> titles;

    private List<String> descriptions;

    private OnItemClickListener mOnItemClickListener;

    public MainWidgetAdapter(List<String> titles, List<String> descriptions) {
        this.titles = titles;
        this.descriptions = descriptions;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefaultViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(titles.get(position), descriptions.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        public void setData(String title, String des) {
            this.tvTitle.setText(title);
            this.tvDescription.setText(des);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
