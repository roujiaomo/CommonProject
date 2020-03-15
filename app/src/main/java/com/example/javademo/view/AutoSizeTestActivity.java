package com.example.javademo.view;

import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
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
        Log.d("qqqqqqqqqqq", "第二页 onResume : " + ActivityUtils.getActivityList().size() + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("qqqqqqqqqqq", "第二页 onDestroy : " + ActivityUtils.getActivityList().size() + "");

    }
}
