package com.example.javademo.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.javademo.base.BaseObserver;
import com.example.javademo.base.BaseViewModel;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.model.NewsModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * VM层 存储请求数据
 */

public class NewsViewModel extends BaseViewModel {
    private NewsModel newsModel;
    //如果不同Activity间共享 需声明Static 保证是同一对象
    public  MutableLiveData<TranslationBean> translationBeanValue = new MutableLiveData<>();
    private Disposable mDisposable;


    public NewsViewModel() {
        newsModel = new NewsModel();
    }

    public void getNews() {
        newsModel.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TranslationBean>(loadStatusLiveData) {
                    @Override
                    public void onSuccess(TranslationBean translationBean) {
                        translationBeanValue.setValue(translationBean);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) { //errorMsg为后台返回的msg字段
                        //处理其他状态码的信息
                        //showDialog(); context 取 application
                    }
                });

    }

    public void cancelNet() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
//        cancelNet();
    }
}
