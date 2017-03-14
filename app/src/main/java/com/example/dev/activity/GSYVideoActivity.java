package com.example.dev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Button;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;

public class GSYVideoActivity extends BaseActivity implements View.OnClickListener {
    private String title;

    private Button mBtnOpenVideo;
    private Button mBtnRvPlayer;
    private Button mBtnRvPlayerMini;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_gsy_video);
    }

    @Override
    protected void initView() {
        mBtnOpenVideo = (Button) findViewById(R.id.btn_open_video);
        mBtnRvPlayer = (Button) findViewById(R.id.btn_rv_player);
        mBtnRvPlayerMini = (Button) findViewById(R.id.btn_rv_player_mini);

    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        mBtnOpenVideo.setOnClickListener(this);
        mBtnRvPlayer.setOnClickListener(this);
        mBtnRvPlayerMini.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        ActivityOptionsCompat activityOptions;
        switch (v.getId()) {
            case R.id.btn_open_video:  //直接一个页面播放的
                intent = new Intent(context, GSYPlayerActivity.class);
                intent.putExtra(GSYPlayerActivity.TRANSITION, true);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Pair pair = new Pair<>(mBtnOpenVideo, GSYPlayerActivity.IMG_TRANSITION);
                    activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context, pair);
                    ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
                } else {
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
                break;
            case R.id.btn_rv_player:  //普通列表播放，只支持全屏，但是不支持屏幕重力旋转，滑出后不持有
                intent = new Intent(context, GSYPlayerRVDisActivity.class);
                intent.putExtra("title", getString(R.string.text_rv_player));
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
                ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
                break;
            case R.id.btn_rv_player_mini:  //支持全屏重力旋转的列表播放，滑动后不会被销毁
                intent = new Intent(context, GSYPlayerRVMiniActivity.class);
                intent.putExtra("title", getString(R.string.text_rv_player_mini));
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
                ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
                break;
            default:
                break;
        }
    }
}
