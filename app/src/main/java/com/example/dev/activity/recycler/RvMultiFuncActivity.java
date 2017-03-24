package com.example.dev.activity.recycler;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.adev.activity.BaseRecyclerActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dev.R;
import com.example.dev.bean.ListItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承 BaseRecyclerActivity 实现列表的示例展示，只显示列表的 Activity 继承它就好了。
 * 实现更多功能，如侧滑菜单，Item 拖拽，滑动删除 Item 等，mSwipeMenuRecyclerView 设置更多的方法即可。
 * 详见与此类同包的其他 activity。
 */
public class RvMultiFuncActivity extends BaseRecyclerActivity<ListItemInfo> {
    private String title;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_rv_demo);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

//        mSwipeMenuRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        mSwipeMenuRecyclerView.addOnItemTouchListener(onItemClickListener);
        addData();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    protected void initItemLayout() {
        setLayoutResId(R.layout.item_main);
        setListType(LINEAR_LAYOUT_MANAGER, true, false, null);
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, ListItemInfo listItemInfo) {
        baseViewHolder.setText(R.id.tv_title, listItemInfo.getTitle());
        baseViewHolder.setText(R.id.tv_des, listItemInfo.getSubTitle());
    }

    private void addData() {
        List<ListItemInfo> list = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.recycler_item_title);
        String[] titlesDes = getResources().getStringArray(R.array.recycler_item_des);
        for (int i = 0; i < titles.length; i++) {
            list.add(new ListItemInfo(titles[i], titlesDes[i]));
        }
        mAdapter.addData(list);
    }

    private RecyclerView.OnItemTouchListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString("title", mAdapter.getItem(position).getTitle());
                    readyGo(SwipeRVDemoActivity.class, bundle);
                    break;
                case 1:
                    break;
            }
        }
    };
}
