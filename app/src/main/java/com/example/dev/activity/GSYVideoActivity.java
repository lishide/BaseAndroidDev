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

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_gsy_video);
    }

    @Override
    protected void initView() {
        mBtnOpenVideo = (Button) findViewById(R.id.btn_open_video);

    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        mBtnOpenVideo.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_video:  //直接一个页面播放的
                Intent intent = new Intent(context, GSYPlayerActivity.class);
                intent.putExtra(GSYPlayerActivity.TRANSITION, true);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Pair pair = new Pair<>(mBtnOpenVideo, GSYPlayerActivity.IMG_TRANSITION);
                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context, pair);
                    ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
                } else {
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
                break;
            default:
                break;
        }
    }
}
