package com.example.dev.activity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.Window;
import android.widget.FrameLayout;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.example.dev.adapter.RvVideoMiniAdapter;
import com.example.dev.bean.VideoModel;
import com.example.dev.listener.SampleVideoListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持全屏重力旋转的列表播放，滑动后不会被销毁
 */
public class GSYPlayerRVMiniActivity extends BaseActivity {
    private String title;

    private RecyclerView mRvGsyPlayer;
    private FrameLayout videoFullContainer;
    private LinearLayoutManager linearLayoutManager;
    private List<VideoModel> dataList = new ArrayList<>();
    private ListVideoUtil listVideoUtil;
    int firstVisibleItem, lastVisibleItem;
    private RvVideoMiniAdapter rvVideoMiniAdapter;

    @Override
    protected void initContentView(Bundle bundle) {
        // 设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_gsy_player_rv_mini);
    }

    @Override
    protected void initView() {
        mRvGsyPlayer = (RecyclerView) findViewById(R.id.rv_gsy_player_mini);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvGsyPlayer.setLayoutManager(linearLayoutManager);
        videoFullContainer = (FrameLayout) findViewById(R.id.video_full_container);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        showLongToast("支持全屏重力旋转的列表播放，滑动后不会被销毁");
        resolveData();
        listVideoUtil = new ListVideoUtil(this);
        listVideoUtil.setFullViewContainer(videoFullContainer);
        listVideoUtil.setHideStatusBar(true);
        listVideoUtil.setHideActionBar(true);

        rvVideoMiniAdapter = new RvVideoMiniAdapter(this, dataList, listVideoUtil);
        mRvGsyPlayer.setAdapter(rvVideoMiniAdapter);

        mRvGsyPlayer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                Debuger.printfLog("firstVisibleItem " + firstVisibleItem
                        + " lastVisibleItem " + lastVisibleItem);
                //大于0说明有播放,//对应的播放列表TAG
                if (listVideoUtil.getPlayPosition() >= 0
                        && listVideoUtil.getPlayTAG().equals(RvVideoMiniAdapter.TAG)) {
                    //当前播放的位置
                    int position = listVideoUtil.getPlayPosition();
                    //不可视的时候
                    if ((position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果是小窗口就不需要处理
                        if (!listVideoUtil.isSmall() && !listVideoUtil.isFull()) {
                            //小窗口
                            int size = CommonUtil.dip2px(GSYPlayerRVMiniActivity.this, 150);
                            //actionbar为true才不会掉下面去
                            listVideoUtil.showSmallVideo(new Point(size, size), true, true);
                        }
                    } else {
                        if (listVideoUtil.isSmall()) {
                            listVideoUtil.smallVideoToNormal();
                        }
                    }
                }
            }
        });

        //小窗口关闭被点击的时候回调处理回复页面
        listVideoUtil.setVideoAllCallBack(new SampleVideoListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                Debuger.printfLog("Duration " + listVideoUtil.getDuration()
                        + " CurrentPosition " + listVideoUtil.getCurrentPositionWhenPlaying());
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {
                super.onQuitSmallWidget(url, objects);
                //大于0说明有播放,//对应的播放列表TAG
                if (listVideoUtil.getPlayPosition() >= 0
                        && listVideoUtil.getPlayTAG().equals(RvVideoMiniAdapter.TAG)) {
                    //当前播放的位置
                    int position = listVideoUtil.getPlayPosition();
                    //不可视的时候
                    if ((position < firstVisibleItem || position > lastVisibleItem)) {
                        //释放掉视频
                        listVideoUtil.releaseVideoPlayer();
                        rvVideoMiniAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    public void onBackPressed() {
        if (listVideoUtil.backFromFull()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listVideoUtil.releaseVideoPlayer();
        GSYVideoPlayer.releaseAllVideos();
    }

    private void resolveData() {
        for (int i = 0; i < 19; i++) {
            VideoModel videoModel = new VideoModel();
            dataList.add(videoModel);
        }
    }
}
