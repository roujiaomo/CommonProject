package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ChildRecyclerView extends RecyclerView {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果只这样处理 则父布局不拦截事件 当父布局的item过多时 父布局会无法滑动而无法展示完全内容
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
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
