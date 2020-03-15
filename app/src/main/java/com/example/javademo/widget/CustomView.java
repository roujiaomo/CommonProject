package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private static final String TAG = "CustomView";
    private int TouchSlop; //滑动最小距离
    private int XVelocity; //横向滑动速度
    private int YVelocity; //纵向滑动速度

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: 事件开始分发");
        return super.dispatchTouchEvent(event);
    }

    /**
     *  当前坐标点
     *  相对于坐标系  event.getRawX()  event.getRawY()
     *  相对于父布局  event.getX()  event.getY()
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //手指刚接触屏幕
                Log.d(TAG, "ACTION_DOWN: ");
                break;
            case MotionEvent.ACTION_MOVE: //手指在屏幕上移动
                Log.d(TAG, "ACTION_MOVE: ");
                //获取当前滑动速度 (velocityTracker对象使用完必须回收)
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(event);
                XVelocity =  (int)velocityTracker.getXVelocity(); //获取横向滑动速度
                YVelocity =  (int)velocityTracker.getYVelocity(); //获取纵向滑动速度
                Log.d(TAG, "横向滑动速度: " +  XVelocity);
                Log.d(TAG, "横向滑动速度: " +  YVelocity);
                velocityTracker.clear(); //回收
                velocityTracker.recycle(); //回收
                break;
            case MotionEvent.ACTION_UP: //手指在屏幕上离开的一瞬间
                Log.d(TAG, "ACTION_UP: ");
                break;
        }
        //获取滑动最小距离
        TouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.d(TAG, "滑动最小距离: " + TouchSlop);
        return super.onTouchEvent(event);
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
