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
import com.example.dev.activity.CityPickerActivity;
import com.example.dev.activity.CoverFlowDemoActivity;
import com.example.dev.activity.GalleryActivity;
import com.example.dev.activity.LikeIosDialogActivity;
import com.example.dev.activity.TabLayDemoActivity;
import com.example.dev.activity.TextDemoActivity;
import com.example.dev.adapter.MainWidgetAdapter;
import com.example.dev.listener.OnItemClickListener;
import com.example.dev.view.ListViewDecoration;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> descriptions;
    private MainWidgetAdapter mMainWidgetAdapter;

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListViewDecoration());

        titles = Arrays.asList(getResources().getStringArray(R.array.main_item_title));
        descriptions = Arrays.asList(getResources().getStringArray(R.array.main_item_des));
        mMainWidgetAdapter = new MainWidgetAdapter(titles, descriptions);
        recyclerView.setAdapter(mMainWidgetAdapter);
        mMainWidgetAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        bundle.putString("title", getString(R.string.text_text_demo));
                        readyGo(TextDemoActivity.class, bundle);
                        break;
                    case 1:
                        bundle.putString("title", getString(R.string.text_coverFlow_demo));
                        readyGo(CoverFlowDemoActivity.class, bundle);
                        break;
                    case 2:
                        bundle.putString("title", getString(R.string.text_iOS_dialog));
                        readyGo(LikeIosDialogActivity.class, bundle);
                        break;
                    case 3:
                        bundle.putString("title", getString(R.string.text_city_picker));
                        readyGo(CityPickerActivity.class, bundle);
                        break;
                    case 4:
                        bundle.putString("title", getString(R.string.text_gallery));
                        readyGo(GalleryActivity.class, bundle);
                        break;
                    case 5:
                        bundle.putString("title", getString(R.string.text_tab_lay));
                        readyGo(TabLayDemoActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    protected void initLogic(View view) {
        setCenterTitle(R.string.tab1);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

}
