package com.example.javademo.widget.event_dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class InViewGroup extends ViewGroup {
    private static final String TAG = "qqqqqqqqqqqqq";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "内层ViewGroup dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "内层ViewGroup dispatchTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "内层ViewGroup dispatchTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "内层ViewGroup dispatchTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "内层ViewGroup dispatchTouchEvent返回值: " + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "内层ViewGroup onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "内层ViewGroup onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "内层ViewGroup onTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "内层ViewGroup onTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "内层ViewGroup onTouchEvent返回值: "+  super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "内层ViewGroup onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "内层ViewGroup onInterceptTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "内层ViewGroup onInterceptTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "内层ViewGroup onInterceptTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "内层ViewGroup onInterceptTouchEvent返回值: "+  super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    public InViewGroup(Context context) {
        super(context);
    }

    public InViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec) {
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
        //稍后会重新写这个方法，能达到wrap_content的效果
        setMeasuredDimension( getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        int layoutWidth = 0;    // 容器已经占据的宽度
        int layoutHeight = 0;   // 容器已经占据的宽度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
        for(int i = 0; i<count; i++){
            View child = getChildAt(i);
            //注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            if(layoutWidth<getWidth()){
                //如果一行没有排满，继续往右排列
                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            } else{
                //排满后换行
                layoutWidth = 0;
                layoutHeight += maxChildHeight;
                maxChildHeight = 0;

                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            }

            layoutWidth += childMeasureWidth;  //宽度累加
            if(childMeasureHeight>maxChildHeight){
                maxChildHeight = childMeasureHeight;
            }

            //确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, right, bottom);
        }

    }
}
