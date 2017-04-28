package com.example.dev.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.adev.activity.BaseActivity;
import com.base.adev.adapter.BaseFragmentStatePagerAdapter;
import com.example.dev.R;
import com.example.dev.fragment.TabLayFragment;

/**
 * MD 商品详情界面示例
 */
public class MDGoodsDtlActivity extends BaseActivity implements View.OnClickListener {
    private String title;

    private LinearLayout frontView, bottomView;
    private FloatingActionButton fab;
    private AnimatorSet showAnim, hiddenAnim;
    private long fWidth, fHeight, bHeight;
    private TextView tvCloseBottom;
    private BaseFragmentStatePagerAdapter mBaseFragmentStatePagerAdapter;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_md_goods_dtl);
    }

    @Override
    protected void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        tvCloseBottom = (TextView) findViewById(R.id.tv_close_bottom);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager_detail);
        mBaseFragmentStatePagerAdapter = new BaseFragmentStatePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mBaseFragmentStatePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        frontView = (LinearLayout) findViewById(R.id.ll_front);
        bottomView = (LinearLayout) findViewById(R.id.ll_bottom);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        for (int i = 0; i < 3; i++) {
            TabLayFragment fragment = new TabLayFragment();
            mBaseFragmentStatePagerAdapter.addFragment(fragment, "推荐 " + i);
            mBaseFragmentStatePagerAdapter.notifyDataSetChanged();
        }

        fab.setOnClickListener(this);
        tvCloseBottom.setOnClickListener(this);

        ViewTreeObserver vto = frontView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fWidth = frontView.getMeasuredWidth();
                fHeight = frontView.getMeasuredHeight();
            }
        });

        ViewTreeObserver sVto = bottomView.getViewTreeObserver();
        sVto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bHeight = bottomView.getMeasuredHeight();
                initShowAnim();
                initHiddenAnim();
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    /**
     * 显示底部布局过程的动画
     */
    private void initShowAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(frontView, "scaleX", 1.0f, 0.8f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(frontView, "scaleY", 1.0f, 0.8f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(frontView, "alpha", 1.0f, 0.5f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(frontView, "translationY", 0, -0.1f * fHeight);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(bottomView, "translationY", bHeight, 0);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                bottomView.setVisibility(View.VISIBLE);
            }
        });
        showAnim = new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim,
                fViewAlphaAnim, fViewScaleYAnim, sViewTransYAnim);
    }

    /**
     * 隐藏底部布局过程的动画
     */
    private void initHiddenAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(frontView, "scaleX", 0.8f, 1.0f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(frontView, "scaleY", 0.8f, 1.0f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(frontView, "alpha", 0.5f, 1.0f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 0f, 10f);
        fViewRotationAnim.setDuration(150);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(200);
        fViewResumeAnim.setStartDelay(150);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(frontView, "translationY", -fHeight * 0.1f, 0);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(bottomView, "translationY", 0, bHeight);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bottomView.setVisibility(View.INVISIBLE);
            }
        });
        hiddenAnim = new AnimatorSet();
        hiddenAnim.playTogether(fViewScaleXAnim, fViewAlphaAnim, fViewRotationAnim, fViewResumeAnim,
                fViewScaleYAnim, fViewTransYAnim, sViewTransYAnim);
        hiddenAnim.setDuration(350);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == fab.getId()) {
            showAnim.start();
        } else if (v.getId() == tvCloseBottom.getId()) {
            hiddenAnim.start();
        }
    }

    @Override
    public void onBackPressed() {
        // 底部布局若正在显示，先隐藏它。
        if (bottomView.getVisibility() == View.VISIBLE) {
            hiddenAnim.start();
            return;
        }
        super.onBackPressed();
    }
}
