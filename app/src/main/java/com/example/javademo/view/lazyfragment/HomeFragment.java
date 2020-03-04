package com.example.javademo.view.lazyfragment;

import android.util.Log;

import com.example.javademo.R;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentHomeBinding;
import com.example.javademo.viewmodel.MainViewModel;

public class HomeFragment extends BaseLazyFragment<MainViewModel, FragmentHomeBinding> {

    private static final String TAG = "HomeFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: "+ "HomeFragment可见");
    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public boolean isReload() {
        return false;
    }
}
