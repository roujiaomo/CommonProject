package com.example.javademo.utils;

import android.content.Context;

import com.example.javademo.widget.ProgressLoading;


public class DialogUtil {
    /**
     * 加载进度条
     *
     * @param
     * @param context
     */
    public static ProgressLoading getProgressDialog(Context context) {
        return ProgressLoading.create(context);
    }


}
