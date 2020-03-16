package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChildRecyclerView extends RecyclerView {
    private static final String TAG = "ChildRecyclerView";
    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private float mLastY = 0;// 记录上次Y位置
    private boolean isParentIntercept = false; //父Rv是否拦截处理事件

    /**
     * 父布局的 dispatchTouchEvent方法
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "子RecyclerView : onInterceptTouchEvent ");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //手指刚接触屏幕
                Log.d(TAG, "ACTION_DOWN: ");
                getParent().requestDisallowInterceptTouchEvent(true); //通知父类不拦截 , 事件直接分发到该层
                break;
            case MotionEvent.ACTION_MOVE: //手指在屏幕上移动
                Log.d(TAG, "ACTION_MOVE: ");
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: //手指在屏幕上离开的一瞬间
                Log.d(TAG, "ACTION_UP: ");
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 思路 :
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "子RecyclerView : onTouchEvent ");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN: ");
                getParent().requestDisallowInterceptTouchEvent(true); //通知父类不拦截 , 事件直接分发到该层
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE: ");
                float nowY = ev.getY();
                isIntercept(nowY);
                if (isParentIntercept) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                mLastY = nowY;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP: ");
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }


        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        //如果只这样处理 则父布局不拦截事件 当父布局的item过多时 父布局会无法滑动而无法展示完全内容
//        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param nowY 当前滑动纵坐标
     */
    private void isIntercept(float nowY) {
        isParentIntercept = false;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            //得到当前界面，最后一个子视图对应的position
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
            //得到当前界面，第一个子视图的position
            firstVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findFirstVisibleItemPosition();
        }
        //得到当前界面可见数据的大小
        int visibleItemCount = layoutManager.getChildCount();
        //得到RecyclerView对应所有数据的大小
        int totalItemCount = layoutManager.getItemCount();
        if (visibleItemCount > 0) {
            //向上滑时, 当视图滑到最后一条并且不可向上滑动时 , 说明触底了 , 此时由父Rv拦截滑动事件
            if (lastVisibleItemPosition == totalItemCount - 1
                    && canScrollVertically(-1)
                    && nowY < mLastY) {
                isParentIntercept = true;
            }
            //向下滑时 当视图滑到第一条时 ,且不可向下滑动时 , 说明顶头了 ,  此时由父Rv拦截滑动事件
            else if (firstVisibleItemPosition == 0) {
                if (ChildRecyclerView.this.canScrollVertically(-1    ) && nowY > mLastY) {
                    // 不能向下滑动
                    isParentIntercept = true;
                }
            }
        }
    }

    public ChildRecyclerView(@NonNull Context context) {
        super(context);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
