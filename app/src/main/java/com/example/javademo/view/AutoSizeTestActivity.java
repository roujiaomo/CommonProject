package com.example.javademo.view;

import com.example.javademo.R;
import com.example.javademo.base.BaseDataActivity;
import com.example.javademo.databinding.ActivityAutoSizeTestBinding;
import com.example.javademo.viewmodel.AutoTestViewModel;

public class AutoSizeTestActivity extends BaseDataActivity<AutoTestViewModel , ActivityAutoSizeTestBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_auto_size_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
