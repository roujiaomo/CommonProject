package com.example.javademo.view.lazyfragment;

import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.javademo.R;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentHomeBinding;
import com.example.javademo.view.AutoSizeTestDataActivity;
import com.example.javademo.view.ServiceTestDataActivity;
import com.example.javademo.viewmodel.MainViewModel;

public class HomeFragment extends BaseLazyFragment<MainViewModel, FragmentHomeBinding> implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        binding.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: "+ "HomeFragment可见");
    }


    @Override
    public void initLiveDataObserve() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test_autoSize:
                ActivityUtils.startActivity(AutoSizeTestDataActivity.class);
                break;
            case R.id.btn_test_service:
                ActivityUtils.startActivity(ServiceTestDataActivity.class);
                break;

        }
    }


    @Override
    public boolean isReload() {
        return false;
    }


}
