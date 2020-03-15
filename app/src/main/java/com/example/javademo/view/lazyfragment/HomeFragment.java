package com.example.javademo.view.lazyfragment;

import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.javademo.R;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentHomeBinding;
import com.example.javademo.view.AutoSizeTestActivity;
import com.example.javademo.view.ServiceTestActivity;
import com.example.javademo.view.eventdispatch.EventConflict1Activity;
import com.example.javademo.view.eventdispatch.EventConflict2Activity;
import com.example.javademo.view.eventdispatch.EventConflict3Activity;
import com.example.javademo.view.eventdispatch.EventConflict4Activity;
import com.example.javademo.viewmodel.MainViewModel;

public class HomeFragment extends BaseLazyFragment<MainViewModel, FragmentHomeBinding> implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
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
                ActivityUtils.startActivity(AutoSizeTestActivity.class);
                break;
            case R.id.btn_test_service:
                ActivityUtils.startActivity(ServiceTestActivity.class);
                break;
            case R.id.btn_event_conflict1:
                ActivityUtils.startActivity(EventConflict1Activity.class);
                break;
            case R.id.btn_event_conflict2:
                ActivityUtils.startActivity(EventConflict2Activity.class);
                break;
            case R.id.btn_event_conflict3:
                ActivityUtils.startActivity(EventConflict3Activity.class);
                break;
            case R.id.btn_event_conflict4:
                ActivityUtils.startActivity(EventConflict4Activity.class);
                break;
        }
    }


    @Override
    public boolean isReload() {
        return false;
    }


}
