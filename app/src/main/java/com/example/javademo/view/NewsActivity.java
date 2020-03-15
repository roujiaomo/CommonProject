package com.example.javademo.view;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.javademo.R;
import com.example.javademo.base.BaseDataActivity;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.databinding.ActivityNewsBinding;
import com.example.javademo.viewmodel.NewsViewModel;

public class NewsActivity extends BaseDataActivity<NewsViewModel, ActivityNewsBinding> implements View.OnClickListener {
    private NewsViewModel newsViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public boolean isShowLoadStatus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_request:
                mViewModel.getNews();
                break;
            case R.id.btn_jump_shareData:
                startActivity(new Intent(this, ShareDataActivity.class));
                break;
            case R.id.btn_jump_rxJavaExample:
                startActivity(new Intent(this, RxJavaExampleActivity.class));
                break;
        }
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
