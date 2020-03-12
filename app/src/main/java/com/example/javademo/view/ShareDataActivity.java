package com.example.javademo.view;

import android.view.View;

import androidx.lifecycle.Observer;

import com.example.javademo.R;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.databinding.ActivityShareDataBinding;
import com.example.javademo.viewmodel.NewsViewModel;

public class ShareDataActivity extends BaseActivity<NewsViewModel, ActivityShareDataBinding> implements View.OnClickListener{


    @Override
    public int getLayoutId() {
        return R.layout.activity_share_data;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_request:
                mViewModel.getNews();
                break;
        }
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLiveDataObserve() {
        //数据请求成功自动触发监听
        mViewModel.translationBeanValue.observe(this, new Observer<TranslationBean>() {
            @Override
            public void onChanged(TranslationBean translationBean) {
                mBinding.setTranslationBean(translationBean);
            }
        });
    }
}
