package com.base.adev.view.coverflow;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.base.adev.R;

import java.util.ArrayList;

public class CoverFlowView extends RelativeLayout {

    public enum CoverFlowGravity {
        TOP, BOTTOM, CENTER_VERTICAL
    }

    public enum CoverFlowLayoutMode {
        MATCH_PARENT, WRAP_CONTENT
    }

    protected CoverFlowGravity mGravity;

    protected CoverFlowLayoutMode mLayoutMode;

    private Scroller mScroller;
    /**
     * To store reflections need to remove
     */
    private ArrayList<View> removeViewArray;

    private SparseArray<View> showViewArray;

    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    private int mWidth; // 控件的宽度

    private float reflectHeightFraction;
    private int reflectGap;

    private int mChildHeight; // child的高度
    private int mChildTranslateY;
//	private int mReflectionTranslateY;

    private int mVisibleChildCount; // 一屏显示的图片数量

    // the visible views left and right 左右两边显示的个数
    protected int VISIBLE_VIEWS = 3;

    private ICoverFlowAdapter mAdapter;

    private float mOffset;
//    private int mLastOffset;

    // 基础alphaֵ
    private final int ALPHA_DATUM = 76;
    private int STANDARD_ALPHA;
    // 基础缩放值
//    private static final float CARD_SCALE = 0.15f;

    private static float MOVE_POS_MULTIPLE = 3.0f;
    private static final int TOUCH_MINIMUM_MOVE = 5;
    private static final float MOVE_SPEED_MULTIPLE = 1;
    private static final float MAX_SPEED = 6.0f;
    private static final float FRICTION = 10.0f;

    private VelocityTracker mVelocity;

    private int firstIndex = 0;

    public CoverFlowView(Context context) {
        super(context);
        init();
        // TODO Auto-generated constructor stub
    }

    public CoverFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        init();
        // TODO Auto-generated constructor stub
    }

    public CoverFlowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttributes(context, attrs);
        init();
        // TODO Auto-generated constructor stub
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageCoverFlowView);

        int totalVisibleChildren = a.getInt(
                R.styleable.ImageCoverFlowView_visibleImage, 3);
        if (totalVisibleChildren % 2 == 0) { // 一屏幕必须是奇数显示
            throw new IllegalArgumentException(
                    "visible image must be an odd number");
        }

        VISIBLE_VIEWS = totalVisibleChildren >> 1; // 计算出左右两两边的显示个数

        reflectHeightFraction = a.getFraction(
                R.styleable.ImageCoverFlowView_reflectionHeight, 100, 0, 0.0f);

        if (reflectHeightFraction > 100) {
            reflectHeightFraction = 100;
        }
        reflectHeightFraction /= 100;
        reflectGap = a.getDimensionPixelSize(
                R.styleable.ImageCoverFlowView_reflectionGap, 0);

        mGravity = CoverFlowGravity.values()[a.getInt(
                R.styleable.ImageCoverFlowView_coverflowGravity,
                CoverFlowGravity.CENTER_VERTICAL.ordinal())];

        mLayoutMode = CoverFlowLayoutMode.values()[a.getInt(
                R.styleable.ImageCoverFlowView_coverflowLayoutMode,
                CoverFlowLayoutMode.WRAP_CONTENT.ordinal())];

        a.recycle();
    }

    private void init() {
        removeAllViews();
        setWillNotDraw(false);
        setClickable(true);

        if (mScroller == null) {
            mScroller = new Scroller(getContext(), new AccelerateDecelerateInterpolator());
        }
        if (showViewArray == null) {
            showViewArray = new SparseArray<View>();
        } else {
            showViewArray.clear();
        }
        if (removeViewArray == null) {
            removeViewArray = new ArrayList<View>();
        } else {
            removeViewArray.clear();
        }

        firstIndex = 0;
        mChildHeight = 0;
        mOffset = 0;
//        mLastOffset = -1;

        isFirstin = true;
        lastMid = 1;
        isChange = true;

        // 计算透明度
        STANDARD_ALPHA = (255 - ALPHA_DATUM) / VISIBLE_VIEWS;

        if (mGravity == null) {
            mGravity = CoverFlowGravity.CENTER_VERTICAL;
        }

        if (mLayoutMode == null) {
            mLayoutMode = CoverFlowLayoutMode.WRAP_CONTENT;
        }

        // 一屏 显示的图片数量
        int visibleCount = (VISIBLE_VIEWS << 1) + 1;

        for (int i = 0; i < visibleCount && mAdapter != null && i < mAdapter.getCount(); ++i) {

            View convertView = null;
            if (removeViewArray.size() > 0) {
                convertView = removeViewArray.remove(0);
            }
            View view = mAdapter.getView(i, convertView, this);
            showViewArray.put(i, view);
            addView(view);
        }

        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mAdapter == null || showViewArray.size() <= 0) {
            return;
        }

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 一屏 显示的图片数量
        int visibleCount = (VISIBLE_VIEWS << 1) + 1;

        // 控件高度
        int avaiblableHeight = heightSize - paddingTop - paddingBottom;

        int maxChildTotalHeight = 0;
        for (int i = 0; i < getChildCount() && i < visibleCount && i < showViewArray.size(); ++i) {

//			View view = showViewArray.get(i+firstIndex);
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

//			final int childHeight = ScreenUtil.dp2px(getContext(), 110);
            final int childHeight = view.getMeasuredHeight();
            final int childTotalHeight = (int) (childHeight + childHeight
                    * reflectHeightFraction + reflectGap);

            // 孩子的最大高度
            maxChildTotalHeight = (maxChildTotalHeight < childTotalHeight) ? childTotalHeight
                    : maxChildTotalHeight;
        }

        // 如果控件模式为确切值 或者 最大是
        if (heightMode == MeasureSpec.EXACTLY
                || heightMode == MeasureSpec.AT_MOST) {
            // if height which parent provided is less than child need, scale
            // child height to parent provide
            // 如果控件高度小于孩子控件高度 则缩放孩子高度为控件高度
            if (avaiblableHeight < maxChildTotalHeight) {
                mChildHeight = avaiblableHeight;
            } else {
                // if larger than, depends on layout mode
                // if layout mode is match_parent, scale child height to parent
                // provide
                // 如果是填充父窗体模式 则将孩子的高度 设为控件高度
                if (mLayoutMode == CoverFlowLayoutMode.MATCH_PARENT) {
                    mChildHeight = avaiblableHeight;
                    // if layout mode is wrap_content, keep child's original
                    // height
                    // 如果是包裹内容 则将孩子的高度设为孩子允许的最大高度
                } else if (mLayoutMode == CoverFlowLayoutMode.WRAP_CONTENT) {
                    mChildHeight = maxChildTotalHeight;

                    // adjust parent's height
                    // 计算出控件的高度
                    if (heightMode == MeasureSpec.AT_MOST) {
                        heightSize = mChildHeight + paddingTop + paddingBottom;
                    }
                }
            }
        } else {
            // height mode is unspecified
            // 如果空间高度 没有明确定义
            // 如果孩子的模式为填充父窗体
            if (mLayoutMode == CoverFlowLayoutMode.MATCH_PARENT) {
                mChildHeight = avaiblableHeight;
                // 如果孩子的模式为包裹内容
            } else if (mLayoutMode == CoverFlowLayoutMode.WRAP_CONTENT) {
                mChildHeight = maxChildTotalHeight;

                // 计算出控件的高度
                // adjust parent's height
                heightSize = mChildHeight + paddingTop + paddingBottom;
            }
        }

        // Adjust movement in y-axis according to gravity
        // 计算出孩子的原点 Y坐标
        if (mGravity == CoverFlowGravity.CENTER_VERTICAL) {// 竖直居中
            mChildTranslateY = (heightSize >> 1) - (mChildHeight >> 1);
        } else if (mGravity == CoverFlowGravity.TOP) {// 顶部对齐
            mChildTranslateY = paddingTop;
        } else if (mGravity == CoverFlowGravity.BOTTOM) {// 底部对齐
            mChildTranslateY = heightSize - paddingBottom - mChildHeight;
        }

//		mReflectionTranslateY = (int) (mChildTranslateY + mChildHeight - mChildHeight
//				* reflectHeightFraction);

        setMeasuredDimension(widthSize, heightSize);
        mVisibleChildCount = visibleCount;
        mWidth = widthSize;

    }

    boolean isFirstin = true; //第一次初始化该控件
    int lastMid = 1;
    boolean isChange = true;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub

        if (mAdapter == null || mAdapter.getCount() <= 0 || showViewArray.size() <= 0) {
            return;
        }

        final float offset = mOffset;
        int i = 0;
        int mid = (int) Math.floor(offset + 0.5);

        //右边孩子的数量
        int rightChild = (mVisibleChildCount % 2 == 0) ? (mVisibleChildCount >> 1) - 1
                : mVisibleChildCount >> 1;
        //左边孩子的数量
        int leftChild = mVisibleChildCount >> 1;

        if (!isFirstin) {

            if (lastMid + 1 == mid) {

                int actuallyPositionStart = getActuallyPosition(lastMid - leftChild);
                View view = showViewArray.get(actuallyPositionStart);
                showViewArray.remove(actuallyPositionStart);
                removeViewArray.add(view);
                removeView(view);

                View convertView = null;
                if (removeViewArray.size() > 0) {
                    convertView = removeViewArray.remove(0);
                }
                int actuallyPositionEnd = getActuallyPosition(mid + rightChild);

                //TODO
                View viewItem = mAdapter.getView(actuallyPositionEnd, convertView, this);

                showViewArray.put(actuallyPositionEnd, viewItem);
                addView(viewItem);

                isChange = true;

            } else if (lastMid - 1 == mid) {

                int actuallyPositionEnd = getActuallyPosition(lastMid + rightChild);
                View view = showViewArray.get(actuallyPositionEnd);
                showViewArray.remove(actuallyPositionEnd);
                removeViewArray.add(view);
                removeView(view);

                View convertView = null;
                if (removeViewArray.size() > 0) {
                    convertView = removeViewArray.remove(0);
                }
                int actuallyPositionstart = getActuallyPosition(mid - leftChild);

                //TODO
                View viewItem = mAdapter.getView(actuallyPositionstart, convertView, this);

                showViewArray.put(actuallyPositionstart, viewItem);
                addView(viewItem, 0);

                isChange = true;
            }

        } else {
            isFirstin = false;
        }

        lastMid = mid;

        // draw the left children
        // 计算左边孩子的位置
        int startPos = mid - leftChild;
        for (i = startPos; i < mid; ++i) {
            //TODO  计算左边孩子的位置
            View child = layoutLeftChild(i, i - offset);

            if (isChange) {
                int actuallyPosition = getActuallyPosition(i);
                mAdapter.getData(child, actuallyPosition);
            }
        }

        // 计算 右边 和 中间
        int endPos = mid + rightChild;

        int j = -VISIBLE_VIEWS;
        for (i = endPos; i >= mid; --i, j = j + 2) {
            //TODO 计算右边和中间孩子的位置
            View child = layoutRightChild(i + j, i - offset);

            if (isChange) {
                int actuallyPosition = getActuallyPosition(i);
                mAdapter.getData(child, actuallyPosition);
            }
        }

        isChange = false;
    }

    private View layoutLeftChild(int position, float offset) {

        //获取实际的position
        int actuallyPosition = getActuallyPosition(position);
        View child = showViewArray.get(actuallyPosition);
        if (child != null) {
            makeChildTransfromer(child, actuallyPosition, offset);
        }
        return child;
    }

    private View layoutRightChild(int position, float offset) {

        //获取实际的position
        int actuallyPosition = getActuallyPosition(position);
        View child = showViewArray.get(actuallyPosition);
        if (child != null) {
            makeChildTransfromer(child, actuallyPosition, offset);
        }

        return child;
    }

    /**
     * <ul>
     * <li>对bitmap进行伪3d变换</li>
     * </ul>
     *
     * @param child
     * @param position
     * @param offset
     */
    private void makeChildTransfromer(View child, int position, float offset) {
//    	child.layout(0, 0, ScreenUtil.dp2px(getContext(), 200),ScreenUtil.dp2px(getContext(), 110));
        child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());

        float scale = 0;
        scale = 1 - Math.abs(offset) * 0.25f;

        // 延x轴移动的距离应该根据center图片决定
        float translateX = 0;

        final int originalChildHeight = (int) (mChildHeight - mChildHeight
                * reflectHeightFraction - reflectGap);

//        final int childTotalHeight = (int) (child.getHeight()
//                + child.getHeight() * reflectHeightFraction + reflectGap);

        final float originalChildHeightScale = (float) originalChildHeight / child.getHeight();

        final float childHeightScale = originalChildHeightScale * scale;

        final int childWidth = (int) (child.getWidth() * childHeightScale);

        final int centerChildWidth = (int) (child.getWidth() * originalChildHeightScale);

//        final int centerChildWidth = (int) (child.getWidth() * childHeightScale);

        int leftSpace = ((mWidth >> 1) - paddingLeft) - (centerChildWidth >> 1);
        int rightSpace = (((mWidth >> 1) - paddingRight) - (centerChildWidth >> 1));

        //计算出水平方向的x坐标
        if (offset <= 0)
            translateX = ((float) leftSpace / VISIBLE_VIEWS)
                    * (VISIBLE_VIEWS + offset) + paddingLeft;

        else
            translateX = mWidth - ((float) rightSpace / VISIBLE_VIEWS)
                    * (VISIBLE_VIEWS - offset) - childWidth
                    - paddingRight;

        //根据offset 算出透明度
        float alpha = 254 - Math.abs(offset) * STANDARD_ALPHA;

        child.setAlpha(0);
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 254) {
            alpha = 254;
        }
        child.setAlpha(alpha / 254.0f);

        float adjustedChildTranslateY = 0;

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(child, "scaleX", 1.0f, childHeightScale);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(child, "scaleY", 1.0f, childHeightScale);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(0);
        //两个动画同时执行  
        animSet.playTogether(anim1, anim2);
        child.setPivotX(0);
        child.setPivotY(child.getHeight() / 2);
        //显示的调用invalidate  
        child.invalidate();
        animSet.setTarget(child);
        animSet.start();

        child.setTranslationX(translateX);
        child.setTranslationY(mChildTranslateY + adjustedChildTranslateY);

    }

    /**
     * 获取顶部Item position
     *
     * @return
     */
    public int getTopViewPosition() {
        return getActuallyPosition(lastMid);
    }

    /**
     * 获取顶部Item View
     *
     * @return
     */
    public View getTopView() {
        return showViewArray.get(getActuallyPosition(VISIBLE_VIEWS + lastMid));
    }

    /**
     * Convert draw-index to index in adapter
     *
     * @param position position to draw
     * @return
     */
    private int getActuallyPosition(int position) {

        int max = mAdapter.getCount();

        position += VISIBLE_VIEWS;
        while (position < 0 || position >= max) {
            if (position < 0) {
                position += max;
            } else if (position >= max) {
                position -= max;
            }
        }

        return position;
    }

    public void setAdapter(ICoverFlowAdapter adapter) {
        mAdapter = adapter;
        init();
    }

    public ICoverFlowAdapter getAdapter() {
        return mAdapter;
    }

    private boolean onTouchMove = false; //是否正在执行触摸移动逻辑

    private View touchViewItem = null;
    private boolean isOnTopView = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (isOnAnimator) {
            return false;
        }
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                onTouchMove = true;
                if (mScroller.computeScrollOffset()) {
                    mScroller.abortAnimation();
                    invalidate();
                    requestLayout();
                }
                touchBegan(event);
                touchViewItem = getTopView();
                isOnTopView = inRangeOfView(touchViewItem, event);
                if (isOnTopView) {
                    sendLongClickAction();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                touchMoved(event);
                removeLongClickAction();
                touchViewItem = null;
                isOnTopView = false;
                return true;
            case MotionEvent.ACTION_UP:
                removeLongClickAction();
                if (isOnTopView && touchViewItem == getTopView()
                        && inRangeOfView(touchViewItem, event)) {

                    if (mTopViewClickLister != null) {
                        mTopViewClickLister.onClick(getTopViewPosition(), getTopView());
                    }
                }
                touchViewItem = null;
                isOnTopView = false;
                touchEnded(event);
                return true;
        }

        return false;
    }

    private Runnable longClickRunnable = null;

    /**
     * 发送长点击事件Runnable
     */
    private void sendLongClickAction() {
        removeLongClickAction();
        longClickRunnable = new Runnable() {
            @Override
            public void run() {
                touchViewItem = null;
                isOnTopView = false;
                if (mTopViewLongClickLister != null) {
                    mTopViewLongClickLister.onLongClick(getTopViewPosition(), getTopView());
                }
            }
        };
        postDelayed(longClickRunnable, 600l);
    }

    /**
     * 移除长点击事件Runnable
     */
    private void removeLongClickAction() {
        if (longClickRunnable != null) {
            removeCallbacks(longClickRunnable);
            longClickRunnable = null;
        }
    }

    private boolean inRangeOfView(View view, MotionEvent ev) {
        Rect frame = new Rect();
        view.getHitRect(frame);
        if (frame.contains((int) ev.getX(), (int) ev.getY())) {
            return true;
        }
        return false;
    }

    private OnTopViewClickLister mTopViewClickLister;
    private OnTopViewLongClickLister mTopViewLongClickLister;

    /**
     * 设置TopView的点击监听
     *
     * @param topViewClickLister
     */
    public void setOnTopViewClickLister(OnTopViewClickLister topViewClickLister) {
        this.mTopViewClickLister = topViewClickLister;
    }

    /**
     * 获取TopView的点击监听
     *
     * @return
     */
    public OnTopViewClickLister getOnTopViewClickLister() {
        return this.mTopViewClickLister;
    }

    /**
     * 设置TopView的长点击监听
     *
     * @param topViewLongClickLister
     */
    public void setOnTopViewLongClickLister(OnTopViewLongClickLister topViewLongClickLister) {
        this.mTopViewLongClickLister = topViewLongClickLister;
    }

    /**
     * 获取TopView的长点击监听
     *
     * @return
     */
    public OnTopViewLongClickLister getOnTopViewLongClickLister() {
        return this.mTopViewLongClickLister;
    }

    public interface OnTopViewClickLister {

        void onClick(int position, View itemView);

    }

    public interface OnTopViewLongClickLister {

        void onLongClick(int position, View itemView);

    }

    private boolean mTouchMoved;
    private float mTouchStartPos;
    private float mTouchStartX;
    private float mTouchStartY;

    private long mStartTime;
    private float mStartOffset;

    private void touchBegan(MotionEvent event) {
        endAnimation();

        float x = event.getX();
        mTouchStartX = x;
        mTouchStartY = event.getY();
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mStartOffset = mOffset;

        mTouchMoved = false;

        mTouchStartPos = (x / mWidth) * MOVE_POS_MULTIPLE - 5;
        mTouchStartPos /= 2;

        mVelocity = VelocityTracker.obtain();
        mVelocity.addMovement(event);
    }

    private Runnable mAnimationRunnable;

    private void endAnimation() {
        if (mAnimationRunnable != null) {
            mOffset = (float) Math.floor(mOffset + 0.5);

            invalidate();
            requestLayout();

            removeCallbacks(mAnimationRunnable);
            mAnimationRunnable = null;
        }
    }

    private void touchMoved(MotionEvent event) {
        float pos = (event.getX() / mWidth) * MOVE_POS_MULTIPLE - 5;
        pos /= 2;

        if (!mTouchMoved) {
            float dx = Math.abs(event.getX() - mTouchStartX);
            float dy = Math.abs(event.getY() - mTouchStartY);
            if (dx < TOUCH_MINIMUM_MOVE && dy < TOUCH_MINIMUM_MOVE)
                return;
            mTouchMoved = true;
        }

        mOffset = mStartOffset + mTouchStartPos - pos;

        invalidate();
        requestLayout();
        mVelocity.addMovement(event);
    }

    private void touchEnded(MotionEvent event) {
        float pos = (event.getX() / mWidth) * MOVE_POS_MULTIPLE - 5;
        pos /= 2;

        if (mTouchMoved || (mOffset - Math.floor(mOffset)) != 0) {
            mStartOffset += mTouchStartPos - pos;
            mOffset = mStartOffset;

            mVelocity.addMovement(event);

            mVelocity.computeCurrentVelocity(1000);
            float speed = mVelocity.getXVelocity();

            speed = (speed / mWidth) * MOVE_SPEED_MULTIPLE;
            if (speed > MAX_SPEED)
                speed = MAX_SPEED;
            else if (speed < -MAX_SPEED)
                speed = -MAX_SPEED;

            startAnimation(-speed);
        } else {
            Log.e(VIEW_LOG_TAG, " touch ==>" + event.getX() + " , " + event.getY());
            onTouchMove = false;
        }

        mVelocity.clear();
        mVelocity.recycle();
    }

    private float mStartSpeed;
    private float mDuration;

    private void startAnimation(float speed) {
        if (mAnimationRunnable != null) {
            onTouchMove = false;
            return;
        }

        float delta = speed * speed / (FRICTION * 2);
        if (speed < 0)
            delta = -delta;

        float nearest = mStartOffset + delta;
        nearest = (float) Math.floor(nearest + 0.5f);

        mStartSpeed = (float) Math.sqrt(Math.abs(nearest - mStartOffset)
                * FRICTION * 2);
        if (nearest < mStartOffset)
            mStartSpeed = -mStartSpeed;

        mDuration = Math.abs(mStartSpeed / FRICTION);
        mStartTime = AnimationUtils.currentAnimationTimeMillis();

        mAnimationRunnable = new Runnable() {
            @Override
            public void run() {
                driveAnimation();
            }
        };
        post(mAnimationRunnable);
    }

    private void driveAnimation() {
        float elapsed = (AnimationUtils.currentAnimationTimeMillis() - mStartTime) / 1000.0f;
        if (elapsed >= mDuration) {
            endAnimation();
            onTouchMove = false;
        } else {
            updateAnimationAtElapsed(elapsed);
            post(mAnimationRunnable);
        }
    }

    private void updateAnimationAtElapsed(float elapsed) {
        if (elapsed > mDuration)
            elapsed = mDuration;

        float delta = Math.abs(mStartSpeed) * elapsed - FRICTION * elapsed
                * elapsed / 2;
        if (mStartSpeed < 0)
            delta = -delta;

        mOffset = mStartOffset + delta;
        invalidate();
        requestLayout();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

//        算出移动到某一个虚拟点时  mOffset的值  然后invalidate 重绘
        if (mScroller.computeScrollOffset()) {
            final int currX = mScroller.getCurrX();

            mOffset = (float) currX / 100;

            invalidate();
        }
    }

    private boolean isOnAnimator = false; //是否正在进行点击切换动画

    /**
     * 翻到前页
     */
    public void gotoPrevious() {
        doAnimator(-1.0f);

    }

    /**
     * 前进到后一页
     */
    public void gotoForward() {
        doAnimator(1.0f);
    }

    public void doAnimator(float target) {
        if (isOnAnimator || onTouchMove) { //如果正在执行点击切换动画 或者 正在执行触摸移动
            return;
        }
        isOnAnimator = true;
        ValueAnimator animator = ValueAnimator.ofFloat(mOffset, mOffset + target);
        animator.setDuration(300).start();
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (Float) animation.getAnimatedValue();

                invalidate();
                requestLayout();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOnAnimator = false;
            }
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true; // 禁止滑动
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}
