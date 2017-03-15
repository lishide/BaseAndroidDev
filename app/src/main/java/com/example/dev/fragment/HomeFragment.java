package com.example.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.adev.fragment.BaseFragment;
import com.example.dev.R;
import com.example.dev.activity.AppbarLayActivity;
import com.example.dev.activity.BMImgClickActivity;
import com.example.dev.activity.CBannerActivity;
import com.example.dev.activity.CityPickerActivity;
import com.example.dev.activity.CoverFlowDemoActivity;
import com.example.dev.activity.GSYVideoActivity;
import com.example.dev.activity.GalleryActivity;
import com.example.dev.activity.LikeIosDialogActivity;
import com.example.dev.activity.MaterialDialogActivity;
import com.example.dev.activity.SwipeRVDemoActivity;
import com.example.dev.activity.TabLayDemoActivity;
import com.example.dev.activity.TextDemoActivity;
import com.example.dev.activity.TextInputLayActivity;
import com.example.dev.adapter.MainWidgetAdapter;
import com.example.dev.bean.ListItemInfo;
import com.example.dev.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_home, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //titles = Arrays.asList(getResources().getStringArray(R.array.main_item_title));
        //descriptions = Arrays.asList(getResources().getStringArray(R.array.main_item_des));
        List<ListItemInfo> listItems = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.main_item_title);
        String[] titlesDes = getResources().getStringArray(R.array.main_item_des);
        for (int i = 0; i < titles.length; i++) {
            listItems.add(new ListItemInfo(titles[i], titlesDes[i]));
        }
        MainWidgetAdapter mMainWidgetAdapter = new MainWidgetAdapter(listItems, mItemClickListener);
        recyclerView.setAdapter(mMainWidgetAdapter);
    }

    private OnItemClickListener mItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    break;
                case 1:
                    bundle.putString("title", getString(R.string.txt_swipe_recycler));
                    readyGo(SwipeRVDemoActivity.class, bundle);
                    break;
                case 2:
                    bundle.putString("title", getString(R.string.text_text_demo));
                    readyGo(TextDemoActivity.class, bundle);
                    break;
                case 3:
                    bundle.putString("title", getString(R.string.txt_video_player));
                    readyGo(GSYVideoActivity.class, bundle);
                    break;
                case 4:
                    bundle.putString("title", getString(R.string.txt_c_banner));
                    readyGo(CBannerActivity.class, bundle);
                    break;
                case 5:
                    bundle.putString("title", getString(R.string.text_coverFlow_demo));
                    readyGo(CoverFlowDemoActivity.class, bundle);
                    break;
                case 6:
                    bundle.putString("title", getString(R.string.text_city_picker));
                    readyGo(CityPickerActivity.class, bundle);
                    break;
                case 7:
                    bundle.putString("title", getString(R.string.text_gallery));
                    readyGo(GalleryActivity.class, bundle);
                    break;
                case 8:
                    bundle.putString("title", getString(R.string.txt_bm_photo));
                    readyGo(BMImgClickActivity.class, bundle);
                    break;
                case 9:
                    bundle.putString("title", getString(R.string.text_tab_lay));
                    readyGo(TabLayDemoActivity.class, bundle);
                    break;
                case 10:
                    bundle.putString("title", getString(R.string.txt_md_appbar_lay));
                    readyGo(AppbarLayActivity.class, bundle);
                    break;
                case 11:
                    bundle.putString("title", getString(R.string.txt_text_input));
                    readyGo(TextInputLayActivity.class, bundle);
                    break;
                case 12:
                    bundle.putString("title", getString(R.string.txt_m_dlg));
                    readyGo(MaterialDialogActivity.class, bundle);
                    break;
                case 13:
                    bundle.putString("title", getString(R.string.text_iOS_dialog));
                    readyGo(LikeIosDialogActivity.class, bundle);
                    break;
            }
        }
    };

    @Override
    protected void initLogic() {
        setCenterTitle(R.string.tab1);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

}
