package com.example.javademo.view.lazyfragment;

import android.util.Log;

import com.example.javademo.R;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentListBinding;
import com.example.javademo.viewmodel.MainViewModel;

/**
 * 骨架屏测试
 */

public class ListFragment extends BaseLazyFragment<MainViewModel, FragmentListBinding> {

    private static final String TAG = "ListFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: "+ "ListFragment可见");
    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public boolean isReload() {
        return true;
    }
}
