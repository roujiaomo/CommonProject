package com.db.commonlibrary.base;

import androidx.lifecycle.ViewModel;

import com.db.commonlibrary.widget.LoadStatusLiveData;


public class BaseViewModel extends ViewModel {
    public LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
}
