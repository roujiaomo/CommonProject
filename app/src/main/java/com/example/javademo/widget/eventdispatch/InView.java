package com.example.javademo.widget.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class InView extends View {
    private static final String TAG = "qqqqqqqqqqqqq";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
//        上方super.dispatchTouchEvent(ev)的源码
        //        if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
//                mOnTouchListener.onTouch(this, event)) {
//            return true;
//        }
//        return onTouchEvent(event); //解释了当界面只有一个ViewGroup时, 为什么调用自身的onTouchEvent()
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "底层view onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "底层view onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "底层view onTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "底层view onTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "底层view onTouchEvent返回值: "+  super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }
    
    public InView(Context context) {
        super(context);
    }

    public InView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
