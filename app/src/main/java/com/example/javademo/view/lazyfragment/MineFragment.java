package com.example.javademo.view.lazyfragment;


import android.util.Log;

import com.example.javademo.R;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentListBinding;
import com.example.javademo.databinding.FragmentMineBinding;
import com.example.javademo.viewmodel.MainViewModel;

public class MineFragment extends BaseLazyFragment<MainViewModel, FragmentMineBinding> {

    private static final String TAG = "MineFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: "+ "MineFragment可见");
    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public boolean isReload() {
        return true;
    }
}
