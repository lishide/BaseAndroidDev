package com.example.dev.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

public class GalleryActivity extends BaseActivity {
    private String title;

    private Button mBtnOpen;
    private RadioButton mRbRadio;
    private RadioButton mRbMulti;
    private TextView tvPicResEvent;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_gallery);
    }

    @Override
    protected void initView() {
        mBtnOpen = (Button) findViewById(R.id.btn_open);
        mRbRadio = (RadioButton) findViewById(R.id.rb_radio);
        mRbMulti = (RadioButton) findViewById(R.id.rb_multi);
        tvPicResEvent = (TextView) findViewById(R.id.tv_picResEvent);
    }

    @Override
    protected void initLogic() {
        setTitle(title);

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRbRadio.isChecked()) {
                    RxGalleryFinal
                            .with(GalleryActivity.this)
                            .image()
                            .radio()
                            .crop()
                            .imageLoader(ImageLoaderType.GLIDE)
                            .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                @Override
                                protected void onEvent(ImageRadioResultEvent imageRadioResultEvent)
                                        throws Exception {
                                    tvPicResEvent.setText("原始路径：\n"
                                            + imageRadioResultEvent.getResult().getOriginalPath()
                                            + "");
                                }
                            })
                            .openGallery();
                } else {
                    RxGalleryFinal
                            .with(GalleryActivity.this)
                            .image()
                            .multiple()
                            .maxSize(8)
                            .imageLoader(ImageLoaderType.GLIDE)
                            .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                                @Override
                                protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent)
                                        throws Exception {
                                    /**
                                     * 这里使用foreach获取了所有图片的原始路径，
                                     * 根据项目需求分别获取单个图片路径。
                                     */
                                    String multiOriPath = "";
                                    for (MediaBean mediaBean : imageMultipleResultEvent.getResult()) {
                                        multiOriPath += mediaBean.getOriginalPath() + "；\n";
                                    }
                                    tvPicResEvent.setText("已选择"
                                            + imageMultipleResultEvent.getResult().size()
                                            + "张图片，原始路径分别为：\n" + multiOriPath);
                                }
                            })
                            .openGallery();
                }
            }
        });

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

}
