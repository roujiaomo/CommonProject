package com.example.javademo.model;

import com.example.javademo.base.BaseResponse;
import com.example.javademo.bean.NewsBean;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.http.HttpUtils;

import io.reactivex.Observable;


public class NewsModel {
    public Observable<BaseResponse<TranslationBean>> getNews() {
        return  HttpUtils.getInstance().getApiService().getNews();
    }
}
