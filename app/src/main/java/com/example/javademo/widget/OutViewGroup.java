package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class OutViewGroup extends ViewGroup {
    private static final String TAG = "qqqqqqqqqqqqq";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "外层ViewGroup dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "外层ViewGroup dispatchTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "外层ViewGroup dispatchTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "外层ViewGroup dispatchTouchEvent: ACTION_UP");
                break;
        }
        Log.d(TAG, "外层ViewGroup dispatchTouchEvent返回值: " + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_UP");
                break;
        }
        Log.d(TAG, "外层ViewGroup onTouchEvent返回值: "+  super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_UP");
                break;
        }
        Log.d(TAG, "外层ViewGroup onInterceptTouchEvent返回值: "+  super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    




    public OutViewGroup(Context context) {
        super(context);
    }

    public OutViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        
    }
}
