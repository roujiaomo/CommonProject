package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * 横向ScrollView 实现原理
 */
public class HorizontalParentViewGroup extends ViewGroup {

    private static final String TAG = "ParentViewGroup";

    //记录上次滑动的坐标 , 默认是 0 , 0 (onInterceptTouchEvent()方法里)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "父布局  onInterceptTouchEvent: ");
        boolean intercepted = false; //是否拦截事件 默认不拦截
        int x  = (int) event.getX(); //当前x坐标
        int y  = (int) event.getY(); //当前y坐标
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //默认不拦截 因为拦截了这个事件序列都会拦截
                Log.d(TAG, "父布局  ACTION_DOWN: ");
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE: //移动过程中 如果横向位移＞纵向位移 则拦截 否则不拦截
                Log.d(TAG, "父布局  ACTION_MOVE: ");
                int xDistance  = x - mLastXIntercept;
                int yDistance  = y - mLastYIntercept;
                //距离有可能为负数, 所以需要取绝对值
                if(Math.abs(xDistance) > Math.abs(yDistance) ){
                    intercepted = true;
                }else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP: //手指在屏幕上离开的一瞬间
                Log.d(TAG, "父布局  ACTION_UP: ");
                intercepted = false;
                break;
        }
        //储存上次的坐标值
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    public HorizontalParentViewGroup(Context context) {
        super(context);
    }

    public HorizontalParentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalParentViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
