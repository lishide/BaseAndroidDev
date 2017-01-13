package com.example.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dev.R;
import com.example.dev.bean.TaskTypeBean;

import java.util.List;

public class TaskTypeAdapter extends BaseAdapter {
    private Context context;
    public List<TaskTypeBean> mArray;

    public TaskTypeAdapter(Context context, List<TaskTypeBean> mArray) {
        this.context = context;
        this.mArray = mArray;
    }

    @Override
    public int getCount() {
        return mArray == null ? 0 : mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_task_type, null);
            holder.tvChannelName = (TextView) convertView.findViewById(R.id.tv_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TaskTypeBean subBean = mArray.get(position);
        holder.tvChannelName.setText(subBean.getName());


        return convertView;
    }

    class ViewHolder {
        TextView tvChannelName;
    }
}
