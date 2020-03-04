package com.example.javademo.base;

import android.app.Application;
import com.example.javademo.viewmodel.NewsViewModel;

public class App extends Application {

    private NewsViewModel newsViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
