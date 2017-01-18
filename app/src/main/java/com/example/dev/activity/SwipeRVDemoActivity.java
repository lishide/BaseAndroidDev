package com.example.dev.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.example.dev.adapter.SwipeRVAdapter;
import com.example.dev.bean.ChannelBean;
import com.example.dev.listener.OnItemClickListener;
import com.example.dev.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

public class SwipeRVDemoActivity extends BaseActivity {
    private String title;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

    private SwipeRVAdapter mSwipeRVAdapter;
    private LinearLayoutManager mLLManager;
    private ArrayList<ChannelBean> channelBeanList = new ArrayList<>();

    private int mLastVisibleItem;  //可见的最后一个Item的pos
    private int mFirstVisibleItem;  //可见的第一个Item的pos
    private boolean isLoadMore = false; //可否加载更多
    private int size = 20;
    private int start_pos;
    private int total_num;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_srv_demo);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        //管理器
        mLLManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSwipeMenuRecyclerView.setLayoutManager(mLLManager);// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
        // 添加滚动监听
        mSwipeMenuRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

        mSwipeRVAdapter = new SwipeRVAdapter(context, channelBeanList);
        mSwipeRVAdapter.setOnItemClickListener(onItemClickListener);
        addDataBean(1);
    }

    /**
     * 添加模拟数据
     *
     * @param type
     */
    private void addDataBean(int type) {
        total_num = 100;
        int iTemp;
        if (type == 1) {
            size = 20;
            iTemp = 0;
        } else {
            size += 20;
            iTemp = size - 20;
        }
        for (int i = iTemp; i < size; i++) {
            ChannelBean channelBean = new ChannelBean();
            channelBean.setImg(i);
            channelBean.setName("RecyclerView item " + i);
            channelBeanList.add(channelBean);
        }

        int mJArrLen = 20; // API 中返回的 list 个数
        start_pos += mJArrLen;
        if (isLoadMore) {
            mSwipeRVAdapter.mArray = channelBeanList;
            mSwipeRVAdapter.notifyItemInserted(start_pos);
        } else {
            mSwipeMenuRecyclerView.setAdapter(mSwipeRVAdapter);
        }

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    /**
     * 刷新监听
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSwipeMenuRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);

                    /**
                     * 这里清空List，重新调用接口请求数据
                     */
                    isLoadMore = false;
                    channelBeanList.clear();
                    //发起API请求
                    start_pos = 0;
                    addDataBean(1);
                }
            }, 2000);
        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            //滑动的状态一变化就会调用
            //这里我们需要的滑到最后一个条目的时候(可见条目是最后一个，而且状态是idle)有一个自动刷新的条目出来
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && mLastVisibleItem + 1 == mSwipeRVAdapter.getItemCount()) {
                /**
                 * 这里比较本地数据个数与网络个数，若小于网络上的总数，则发起请求
                 */
                if (start_pos < total_num) {
                    isLoadMore = true;
                    //发起API请求
                    addDataBean(2);
                    showShortToast("滑到最底部了，加载更多吧");
                } else {
                    showShortToast("已全部加载完成了");
                }
            }
            //解决 RecyclerView 的 item 并没有达到第一个也能下拉刷新
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mFirstVisibleItem == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            //滑动的时候会调用的函数
            mLastVisibleItem = mLLManager.findLastVisibleItemPosition();
            mFirstVisibleItem = mLLManager.findFirstVisibleItemPosition();
            /*if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                Toast.makeText(context, "滑到最底部了，去加载更多吧！", Toast.LENGTH_SHORT).show();
            }*/
        }
    };


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            showShortToast("我是第" + position + "条。");
        }
    };

}
