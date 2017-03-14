package com.example.dev.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.Window;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.example.dev.adapter.RvVideoDisAdapter;
import com.example.dev.bean.VideoModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通列表播放，只支持全屏，但是不支持屏幕重力旋转，滑出后不持有
 */
public class GSYPlayerRVDisActivity extends BaseActivity {
    private String title;

    private RecyclerView mRvGsyPlayer;
    private LinearLayoutManager linearLayoutManager;
    private List<VideoModel> dataList = new ArrayList<>();

    @Override
    protected void initContentView(Bundle bundle) {
        // 设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_gsy_player_rv_dis);
    }

    @Override
    protected void initView() {
        mRvGsyPlayer = (RecyclerView) findViewById(R.id.rv_gsy_player);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        showLongToast("普通列表播放，只支持全屏，但是不支持屏幕重力旋转，滑出后不持有");
        resolveData();
        final RvVideoDisAdapter rvVideoDisAdapter = new RvVideoDisAdapter(this, dataList);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvGsyPlayer.setLayoutManager(linearLayoutManager);
        mRvGsyPlayer.setAdapter(rvVideoDisAdapter);

        mRvGsyPlayer.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RvVideoDisAdapter.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoPlayer.releaseAllVideos();
                        rvVideoDisAdapter.notifyDataSetChanged();
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
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
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
        GSYVideoPlayer.releaseAllVideos();
    }

    private void resolveData() {
        for (int i = 0; i < 19; i++) {
            VideoModel videoModel = new VideoModel();
            dataList.add(videoModel);
        }
    }
}
