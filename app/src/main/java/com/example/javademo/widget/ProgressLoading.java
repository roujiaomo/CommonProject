package com.example.javademo.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.javademo.R;

public class ProgressLoading extends Dialog {

    private static AnimationDrawable animDrawable = null;
    private static int gravity;
    private static Window window;

    public ProgressLoading(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        gravity = getWindow().getAttributes().gravity;
        window = getWindow();
    }

    public static ProgressLoading create(Context context) {
        ProgressLoading mDialog = new ProgressLoading(context, R.style.LightProgressDialog);
        //设置布局
        mDialog.setContentView(R.layout.common_progress_dialog);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.2f;
        window.setAttributes(lp);
        ImageView loadingView = mDialog.findViewById(R.id.iv_loading);
        animDrawable = (AnimationDrawable) loadingView.getBackground();
        return mDialog;
    }

    /*
          显示加载对话框，动画开始
       */
    public void showLoading() {
        super.show();
        animDrawable.start();
    }

    /*
        显示加载信息
     */
    public void showMessage(String msg) {
        findViewById(R.id.tv_Message).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_Message)).setText(msg);
    }

    /*
        隐藏加载对话框，动画停止
     */
    public void hideLoading() {
        super.dismiss();
        animDrawable.stop();
    }

}
