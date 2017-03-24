package com.base.adev.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.base.adev.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerActivity<T> extends BaseActivity {
    private static final String TAG = "BaseRecyclerActivity";
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected SwipeMenuRecyclerView mSwipeMenuRecyclerView;
    protected RvAdapter mAdapter;
    /**
     * list 布局
     */
    protected static final int LINEAR_LAYOUT_MANAGER = 0;
    /**
     * grid 布局
     */
    protected static final int GRID_LAYOUT_MANAGER = 1;
    /**
     * 瀑布流布局
     */
    protected static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
    /**
     * 默认为 0 单行布局
     */
    private int mListType = 0;
    /**
     * 排列方式默认垂直
     */
    private boolean mIsVertical = true;
    /**
     * grid 布局与瀑布流布局默认行数
     */
    private int mSpanCount = 1;
    /**
     * 子布局 id
     */
    private int layoutResId = -1;
    /**
     * 是否可刷新，默认不可以
     */
    private boolean mCanRefresh = false;
    /**
     * 刷新监听
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = null;

    @Override
    protected void initView() {
        initItemLayout();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_swipe_refresh_lay);
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.base_rv_list);
        setRefreshEnable(mCanRefresh, mOnRefreshListener);
        chooseListType(mListType, mIsVertical);
        if (-1 == layoutResId) {
            throw new RuntimeException("layoutResId is null!");
        }
        mAdapter = new RvAdapter(layoutResId, new ArrayList<T>());
        mSwipeMenuRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置子布局 layout
     *
     * @param layoutResId 子布局 layout
     */
    public void setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }

    /**
     * 初始化子布局
     */
    protected abstract void initItemLayout();

    /**
     * 设置下拉刷新
     *
     * @param canRefresh        下拉刷新功能是否可用，true：允许，false：禁止。
     * @param onRefreshListener 刷新监听
     */
    protected void setRefreshEnable(boolean canRefresh,
                                    SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        if (canRefresh) {
            mSwipeRefreshLayout.setEnabled(true);
            mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置加载更多
     *
     * @param mOnScrollListener 滚动监听
     */
    protected void setLoadMoreEnable(RecyclerView.OnScrollListener mOnScrollListener) {
        // 添加滚动监听
        mSwipeMenuRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    /**
     * 设置布局类型
     *
     * @param type              布局管理 type
     * @param isVertical        是否为垂直布局，true：垂直布局，false：横向布局
     * @param canRefresh        是否可刷新
     * @param onRefreshListener 刷新监听，如果 canRefresh 为 true，必须传 onRefreshListener，若为 false，传 null
     */
    protected void setListType(int type, boolean isVertical, boolean canRefresh,
                               SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        mListType = type;
        mIsVertical = isVertical;
        mCanRefresh = canRefresh;
        mOnRefreshListener = onRefreshListener;
    }

    /**
     * 设置行数
     *
     * @param spanCount 行数
     */
    protected void setSpanCount(int spanCount) {
        if (spanCount > 0)
            mSpanCount = spanCount;
    }

    /**
     * 设置布局管理器
     *
     * @param listType   布局类型
     * @param isVertical 是否为垂直布局
     */
    private void chooseListType(int listType, boolean isVertical) {
        switch (listType) {
            case LINEAR_LAYOUT_MANAGER:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                mSwipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case GRID_LAYOUT_MANAGER:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, mSpanCount);
                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
                mSwipeMenuRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                        (mSpanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
                mSwipeMenuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                mSwipeMenuRecyclerView.setLayoutManager(layoutManager);
                break;
        }
    }

    /**
     * adapter 内的处理
     *
     * @param baseViewHolder BaseViewHolder
     * @param t              泛型 T
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

    public class RvAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public RvAdapter(int layoutResId, List<T> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            MyHolder(baseViewHolder, t);
        }
    }
}
