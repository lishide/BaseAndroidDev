package com.example.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dev.R;
import com.example.dev.bean.VideoModel;
import com.example.dev.listener.SampleVideoListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class RvVideoDisAdapter extends RecyclerView.Adapter<RvVideoDisAdapter.DefaultViewHolder> {
    public final static String TAG = "RvVideoDisAdapter";

    private List<VideoModel> itemDataList = null;
    private Context context = null;

    public RvVideoDisAdapter(Context context, List<VideoModel> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
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
                .inflate(R.layout.list_video_item_dis, parent, false));
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(position, itemDataList.get(position));
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        StandardGSYVideoPlayer gsyVideoPlayer;
        ImageView imageView;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = new ImageView(context);
            gsyVideoPlayer = (StandardGSYVideoPlayer) itemView.findViewById(R.id.video_item_player);
        }

        public void setData(int position, VideoModel videoModel) {
            //增加封面
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (position % 2 == 0) {
                imageView.setImageResource(R.drawable.ic_test_2);
            } else {
                imageView.setImageResource(R.drawable.ic_test_4);
            }
            if (imageView.getParent() != null) {
                ViewGroup viewGroup = (ViewGroup) imageView.getParent();
                viewGroup.removeView(imageView);
            }
            gsyVideoPlayer.setThumbImageView(imageView);

            final String url = "http://baobab.wdjcdn.com/14564977406580.mp4";

            //默认缓存路径
            gsyVideoPlayer.setUp(url, true, null, "这是title");

            //增加title
            gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);

            //设置返回键
            gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

            //设置全屏按键功能
            gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resolveFullBtn(gsyVideoPlayer);
                }
            });
            gsyVideoPlayer.setRotateViewAuto(true);
            gsyVideoPlayer.setLockLand(true);
            gsyVideoPlayer.setPlayTag(TAG);
            gsyVideoPlayer.setShowFullAnimation(true);
            //循环
            //gsyVideoPlayer.setLooping(true);
            gsyVideoPlayer.setNeedLockFull(true);

            //gsyVideoPlayer.setSpeed(2);

            gsyVideoPlayer.setPlayPosition(position);

            gsyVideoPlayer.setStandardVideoAllCallBack(new SampleVideoListener() {
                @Override
                public void onPrepared(String url, Object... objects) {
                    super.onPrepared(url, objects);
                    Debuger.printfLog("onPrepared");
                    if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                        //静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                }

                @Override
                public void onQuitFullscreen(String url, Object... objects) {
                    super.onQuitFullscreen(url, objects);
                    //全屏不静音
                    GSYVideoManager.instance().setNeedMute(true);
                }

                @Override
                public void onEnterFullscreen(String url, Object... objects) {
                    super.onEnterFullscreen(url, objects);
                    GSYVideoManager.instance().setNeedMute(false);
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }
}
