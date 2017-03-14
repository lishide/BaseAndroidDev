package com.example.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dev.R;
import com.example.dev.bean.VideoModel;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.List;

public class RvVideoMiniAdapter extends RecyclerView.Adapter<RvVideoMiniAdapter.DefaultViewHolder> {
    public final static String TAG = "RvVideoMiniAdapter";

    private List<VideoModel> itemDataList = null;
    private Context context = null;
    private ListVideoUtil listVideoUtil;

    public RvVideoMiniAdapter(Context context, List<VideoModel> itemDataList,
                              ListVideoUtil listVideoUtil) {
        this.itemDataList = itemDataList;
        this.context = context;
        this.listVideoUtil = listVideoUtil;
    }

    @Override
    public int getItemCount() {
        return itemDataList == null ? 0 : itemDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefaultViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_video_item_mini, parent, false));
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(position, itemDataList.get(position));
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        FrameLayout listItemContainer;
        ImageView listItemBtn;
        ImageView imageView;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = new ImageView(context);
            listItemBtn = (ImageView) itemView.findViewById(R.id.list_item_btn);
            listItemContainer = (FrameLayout) itemView.findViewById(R.id.list_item_container);
        }

        public void setData(final int position, VideoModel videoModel) {
            //增加封面
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.ic_test_2);

            listVideoUtil.addVideoPlayer(position, imageView, TAG, listItemContainer, listItemBtn);

            listItemBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                    //listVideoUtil.setLoop(true);
                    listVideoUtil.setPlayPositionAndTag(position, TAG);
                    final String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
                    //listVideoUtil.setCachePath(new File(FileUtils.getPath()));
                    listVideoUtil.startPlay(url);
                }
            });
        }

        @Override
        public void onClick(View v) {
        }

    }
}
