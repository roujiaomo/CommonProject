package com.db.commonlibrary.base;

import androidx.lifecycle.ViewModel;

import com.example.javademo.widget.LoadStatusLiveData;

public class BaseViewModel extends ViewModel {
    public LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
}
