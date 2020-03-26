package com.example.javademo.view.eventdispatch;

import com.example.javademo.R;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityEventSampleBinding;

/**
 * 外层 ,内层, 底部都不拦截处理事件
 *
 * 外层ViewGroup dispatchTouchEvent: ACTION_DOWN
 * 外层ViewGroup onInterceptTouchEvent: ACTION_DOWN
 * 内层ViewGroup dispatchTouchEvent: ACTION_DOWN
 * 内层ViewGroup onInterceptTouchEvent: ACTION_DOWN
 * 底层view dispatchTouchEvent: ACTION_DOWN
 * 底层view onTouchEvent: ACTION_DOWN
 * 内层ViewGroup onTouchEvent: ACTION_DOWN
 * 外层ViewGroup onTouchEvent: ACTION_DOWN
 * 
 */
public class EventSampleActivity extends BaseActivity<ActivityEventSampleBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_event_sample;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
