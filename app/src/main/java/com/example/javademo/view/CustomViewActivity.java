package com.example.javademo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.javademo.R;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityCustomViewBinding;

public class CustomViewActivity extends BaseActivity<ActivityCustomViewBinding> {

    private static final String TAG = "CustomViewActivity";
    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    public void initView() {
//            mBinding.customView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return true;
//                }
//            });
        Log.d(TAG, "CustomViewActivity初始化 ");
        mBinding.customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了CustomView ");
            }
        });
    }

    @Override
    public void initData() {

    }
}
