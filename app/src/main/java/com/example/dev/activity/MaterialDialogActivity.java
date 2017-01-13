package com.example.dev.activity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.example.dev.adapter.TaskTypeAdapter;
import com.example.dev.bean.TaskTypeBean;

import java.util.ArrayList;

import me.drakeet.materialdialog.MaterialDialog;

public class MaterialDialogActivity extends BaseActivity {

    private String title;
    private MaterialDialog mMaterialDialog;
    private ArrayList<TaskTypeBean> typeBeanList;
    public static final String[] typeNames = {"分类1", "分类2", "分类3", "分类4"};
    public static final int[] typeId = {1, 2, 3, 4};
    private TaskTypeAdapter listAdapter;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_material_dlg);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    public void init(View v) {
        mMaterialDialog = new MaterialDialog(context);
        showShortToast("Initializes successfully.");
    }

    public void show(View v) {
        if (mMaterialDialog != null) {
            // You can change the title anytime.
            mMaterialDialog.setTitle("MaterialDialog")
                    .setMessage("Hi! This is a MaterialDialog." +
                            " It's very easy to use, you just new and show() it" +
                            " then the beautiful AlertDialog will show automatically." +
                            " It is artistic, conforms to Google Material Design." +
                            " I hope that you will like it, and enjoy it. ^ ^")
                    //mMaterialDialog.setBackgroundResource(R.drawable.bg_dlg_cus1);
                    .setPositiveButton("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            showShortToast("Ok");
                        }
                    })
                    .setNegativeButton("CANCEL", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            showShortToast("Cancel");
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            showShortToast("onDismiss");

                        }
                    })
                    .show();
            // You can change the message anytime.
            // mMaterialDialog.setMessage("嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^");
        } else {
            showShortToast("You should init firstly!");
        }
    }

    static int i = 0;

    public void setView(View v) {
        switch (v.getId()) {
            case R.id.button_set_view:
                mMaterialDialog = new MaterialDialog(context);
                View view = LayoutInflater.from(this).inflate(R.layout.progressbar_item, null);
                mMaterialDialog.setCanceledOnTouchOutside(true);
                mMaterialDialog.setView(view).show();
                break;
            case R.id.button_set_background:
                mMaterialDialog = new MaterialDialog(context);
                if (i % 2 != 0) {
                    mMaterialDialog.setBackgroundResource(R.drawable.bg_dlg_cus1);
                } else {
                    Resources res = getResources();
                    Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.bg_dlg_cus2);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
                    mMaterialDialog.setBackground(bitmapDrawable);
                }
                mMaterialDialog.setCanceledOnTouchOutside(true).show();
                i++;
                showShortToast("Try to click again ~");
                break;
            case R.id.button_set_contentView:
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1);
                for (int j = 0; j < 38; j++) {
                    arrayAdapter.add("This is item " + j);
                }

                ListView listView = new ListView(this);
                listView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (8 * scale + 0.5f);
                listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
                listView.setDividerHeight(0);
                listView.setAdapter(arrayAdapter);

                final MaterialDialog alertList = new MaterialDialog(context)
                        .setTitle("MaterialDialog").setContentView(listView);

                alertList.setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertList.dismiss();
                    }
                });

                alertList.show();
                break;
            case R.id.button_set_contentView2:
                typeBeanList = new ArrayList<>();
                for (int i = 0; i < typeNames.length; i++) {
                    TaskTypeBean taskTypeBean = new TaskTypeBean();
                    taskTypeBean.setName(typeNames[i]);
                    taskTypeBean.setId(typeId[i]);
                    typeBeanList.add(taskTypeBean);
                }

                ListView lvBase = new ListView(context);
                lvBase.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                listAdapter = new TaskTypeAdapter(context, typeBeanList);

                float scale2 = getResources().getDisplayMetrics().density;
                int dpAsPixels2 = (int) (8 * scale2 + 0.5f);
                lvBase.setPadding(0, dpAsPixels2, 0, dpAsPixels2);
                lvBase.setDividerHeight(1);
                lvBase.setAdapter(listAdapter);

                final MaterialDialog alertList2 = new MaterialDialog(context).setTitle(
                        "选择分类").setContentView(lvBase).setCanceledOnTouchOutside(true);
                lvBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TaskTypeBean taskTypeBean = typeBeanList.get(i);
                        showShortToast("id：" + taskTypeBean.getId() + "，name：" + taskTypeBean.getName());

                        alertList2.dismiss();
                    }
                });
//                alert.setPositiveButton("OK", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alert.dismiss();
//                    }
//                });
                alertList2.show();
                break;
            case R.id.button_set_contentViewById:
                final MaterialDialog alert = new MaterialDialog(context)
                        .setTitle("MaterialDialog")
                        .setContentView(R.layout.custom_message_content);

                alert.setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });
                alert.show();
                break;
            case R.id.button_set_notitle:
                final MaterialDialog materialDialog = new MaterialDialog(context);
                materialDialog.setMessage("This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. "
                        + "This is a dialog without title. ")
                        .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                materialDialog.dismiss();
                            }
                        });
                materialDialog.show();
                break;
        }
    }

}
