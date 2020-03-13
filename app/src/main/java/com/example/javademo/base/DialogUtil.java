package com.example.javademo.base;

import android.content.Context;

import com.example.javademo.R;
import com.example.javademo.widget.CenterProgressDialog;


/**
 * Created by ly343 on 2019/8/6.
 */

public class DialogUtil {
    /**
     * 进度条 环形
     *
     * @param
     * @param context
     */
    public static CenterProgressDialog getProgressDialog(String message, Context context) {
        CenterProgressDialog centerProgressDialog = new CenterProgressDialog(context, message,
                R.style.dialog_custom);
        return centerProgressDialog;
    }

}
