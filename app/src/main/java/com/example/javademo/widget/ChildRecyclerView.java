package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 子 : 内层rv  父 : 外层rv
 *
 * 思路 : 拦截Rv的down和 move事件
 * onTouchEvent()里 处理 move事件 :
 * 当子Rv显示第一条且不能下拉时 , 把事件交给父Rv处理
 * 当子Rv显示最后一条且不能上拉时 , 把事件交给父Rv处理
 *
 * Down事件 : (都不拦截)
 * RecyclerView源码默认不拦截Down事件
 * Down事件 从父Rv的 dispatchTouchEvent()方法传递到 子Rv的dispatchTouchEvent()方法
 * 子Rv在 dispatchTouchEvent()调用子Rv的 onInterceptTouchEvent()方法 , 返回值为 super.onInterceptTouchEvent(ev) ,
 * 返回值为true , 即自身并未拦截消费该事件 , 同时因为调用了 getParent().requestDisallowInterceptTouchEvent(true) ,
 * 这时父Rv是无法拦截其余事件的.
 *
 * move事件 : (拦截根据情况消费或者返给父Rv消费)
 * RecyclerView源码默认拦截 move事件
 * 但由于在down里的设置 , 此时产生的move事件 ,全部由子Rv拦截 , 当产生一个move事件时 ,
 * 判断这个事件的纵坐标的位置 :
 * 如果子View滑动到第一条且不能下滑时 , 且手指是向下滑的( 当前纵坐标大于上次的纵坐标 )
 * 或 滑动到最后一条且不能上滑时 ,且手指是向上滑的( 当前纵坐标小于上次的纵坐标 )
 * 此时 调用 getParent().requestDisallowInterceptTouchEvent(false) ,
 *
 * 父Rv拦截的第一个move事件 , 只是拦截到了 ,但并未消费(未走 onTouchEvent()) ,这个事件首先会被系统变成一个Cancel事件传递给子Rv,
 * 当下个move事件被父Rv拦截后 , 父Rv才走开始处理事件 (走 onTouchEvent()) , 后续事件将直接传递给父Rv的onTouchEvent()处理，
 * 而不会再传递给父Rv的onInterceptTouchEvent（），因该方法一旦返回一次true，就再也不会被调用了。
 *
 *
 * 即通知父Rv可以拦截了 ,下个move动作由父Rv处理
 * 否则 由子Rv自己消费该事件 即内层滑动
 *
 *
 *
 */
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
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "ACTION_DOWN: ");
                //通知父类不拦截 , 事件直接分发到该层 , 但是并未拦截 , 未消费
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP: ");
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
        mLastY = event.getY();
        //不允许父View拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
        break;
        case MotionEvent.ACTION_MOVE:
        float nowY = event.getY();
        isIntercept(nowY);
        if (isBottomToTop||isTopToBottom){
        getParent().requestDisallowInterceptTouchEvent(false);
        return false;
        }else{
        getParent().requestDisallowInterceptTouchEvent(true);
        }
        mLastY = nowY;
        break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
        getParent().requestDisallowInterceptTouchEvent(false);
        break;
        }
        return super.onTouchEvent(event);
        }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param nowY 当前滑动纵坐标
     */
    private void isIntercept(float nowY) {
        isParentIntercept = false;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            //得到当前界面，最后一个子视图对应的position
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
            //得到当前界面，第一个子视图的position
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager)
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
                if (ChildRecyclerView.this.canScrollVertically(1) && nowY > mLastY) {
                    // 不能向下滑动
                    isParentIntercept = true;
                }
            }
        }
    }

    private boolean isTopToBottom = false;
    private boolean isBottomToTop = false;
    private void isIntercept2(float nowY){

        isTopToBottom = false;
        isBottomToTop = false;

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
        if (visibleItemCount>0) {
            if (lastVisibleItemPosition == totalItemCount - 1) {
                //最后视图对应的position等于总数-1时，说明上一次滑动结束时，触底了
                if (canScrollVertically(-1) && nowY < mLastY) {
                    // 不能向上滑动
                    isBottomToTop = true;
                } else {
                }
            } else if (firstVisibleItemPosition == 0) {
                //第一个视图的position等于0，说明上一次滑动结束时，触顶了
                if (canScrollVertically(1) && nowY > mLastY) {
                    // 不能向下滑动
                    isTopToBottom = true;
                } else {
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
