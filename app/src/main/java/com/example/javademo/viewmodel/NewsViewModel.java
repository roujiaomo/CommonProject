package com.example.javademo.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.javademo.base.BaseObserver;
import com.example.javademo.base.BaseViewModel;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.model.NewsModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * VM层 存储请求数据
 */

public class NewsViewModel extends BaseViewModel {
    private NewsModel newsModel;
    /**同一Activity下 ,例如宿主Activity与它的Fragment数据直接共享 ,ViewModel的数据无需声明Static
     *
     * 如果不同Activity间共享 需声明Static 保证是同一对象
     */
    public  MutableLiveData<TranslationBean> translationBeanValue = new MutableLiveData<>();


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

    /**
     * ViewModel创建时 , Activity未传引用给ViewModel ,但ViewModel与Activity生命周期绑定
     * Activity走到onDestroy , ViewModel走onClear()方法销毁对象 ,使V层与VM层解耦
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
