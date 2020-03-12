package com.example.javademo.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.blankj.utilcode.util.ServiceUtils;
import com.example.javademo.R;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.base.BaseViewModel;
import com.example.javademo.databinding.ActivityTestServiceBinding;
import com.example.javademo.service.ExampleService;

public class ServiceTestActivity extends BaseActivity<BaseViewModel ,ActivityTestServiceBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test_service;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startService:
                ServiceUtils.startService(ExampleService.class);
                break;
            case R.id.btn_stopService:
                ServiceUtils.stopService(ExampleService.class);
                break;
            case R.id.btn_bindService:
                ServiceUtils.bindService(ExampleService.class, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unBindService:
                ServiceUtils.unbindService(conn);
                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ExampleService.MyBinder binder = (ExampleService.MyBinder)service;
            ExampleService exampleService = binder.getService();
            exampleService.fromActivity();
            //拿到service对象 处理service对象数据
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void initLiveDataObserve() {

    }
}
