package com.example.javademo.view;

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
    }

    @Override
    public void initData() {

    }
}
