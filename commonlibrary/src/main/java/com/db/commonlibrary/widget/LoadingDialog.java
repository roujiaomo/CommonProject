package com.db.commonlibrary.widget;

import android.app.ProgressDialog;
import android.content.Context;


public class LoadingDialog {
    private ProgressDialog progressDialog;

    public LoadingDialog(Context context ) {
        progressDialog = new ProgressDialog(context);
        //依次设置标题,内容,是否用取消按钮关闭,是否显示进度
        progressDialog.setCancelable(true);
        //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    public void show(String msg) {
        progressDialog.setTitle(msg);
        progressDialog.show();
    }

    public void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
