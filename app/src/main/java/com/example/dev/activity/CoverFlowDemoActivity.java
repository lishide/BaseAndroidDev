package com.example.dev.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.example.dev.adapter.CoverFlowAdapter;
import com.example.dev.bean.ChannelBean;
import com.img.coverflow.widget.CoverFlowView;

import java.util.ArrayList;
import java.util.List;

public class CoverFlowDemoActivity extends BaseActivity implements View.OnClickListener {
    private String title;

    private CoverFlowView coverFlowView;
    private Button btnPrevious;
    private Button btnForward;
    private Button btnGetTop;
    private Button btnGetTopView;

    private List<ChannelBean> channelBeanList;
    public static final int[] channelImgs = {R.drawable.ic_ns1, R.drawable.ic_ns2,
            R.drawable.ic_ns3, R.drawable.ic_ns4, R.drawable.ic_ns5};
    public static final String[] channelNames = {"图片1", "图片2", "图片3", "图片4", "图片5"};

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_coverflow);

    }

    @Override
    protected void initView() {
        coverFlowView = (CoverFlowView) findViewById(R.id.coverflow);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        btnForward = (Button) findViewById(R.id.btn_forward);
        btnGetTop = (Button) findViewById(R.id.btn_get_top);
        btnGetTopView = (Button) findViewById(R.id.btn_get_top_view);
        btnPrevious.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnGetTop.setOnClickListener(this);
        btnGetTopView.setOnClickListener(this);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);
        initListDataAndAction();

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    private void initListDataAndAction() {
        channelBeanList = new ArrayList<>();
        for (int i = 0; i < channelNames.length; i++) {
            ChannelBean channelBean = new ChannelBean();
            channelBean.setImg(channelImgs[i]);
            channelBean.setName(channelNames[i]);
            channelBeanList.add(channelBean);
        }

        CoverFlowAdapter coverFlowAdapter = new CoverFlowAdapter(context, channelBeanList);
        coverFlowView.setAdapter(coverFlowAdapter);

        // 给coverFlowView的TOPView 添加点击事件监听
        coverFlowView.setOnTopViewClickLister(new CoverFlowView.OnTopViewClickLister() {
            @Override
            public void onClick(int position, View itemView) {
                ChannelBean channelBean = channelBeanList.get(position);

                showShortToast("点击事件 position：" + position + "， text：" + channelBean.getName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_previous:
                coverFlowView.gotoPrevious();//向前一页
                break;
            case R.id.btn_forward:
                coverFlowView.gotoForward();//向后一页
                break;
            case R.id.btn_get_top://获取最上面Item的position
                int position = coverFlowView.getTopViewPosition();
                showShortToast("" + position);
                break;
            case R.id.btn_get_top_view://获取最上面Item的View
                CoverFlowAdapter.Holder holder = (CoverFlowAdapter.Holder) coverFlowView.getTopView().getTag();
                showShortToast(holder.tv.getText() + "");
                break;
            default:
                break;
        }
    }

}
