package com.example.javademo.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.javademo.R;


/**
 * Created by ly343 on 2019/8/6.
 * 加载dialog
 */

public class CenterProgressDialog extends ProgressDialog {

    private String content;
    private TextView textView;
    private Context dialogContext;

    public CenterProgressDialog(Context context, String content, int theme) {
        super(context, theme);
        this.content = content;

    }

    public CenterProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogContext = getContext();
        init(getContext());
    }

    /**
     * 展示同样信息时 初始化一次 调用无参show()
     * @param context
     */
    private void init(Context context) {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_progress_layout);
         textView =  findViewById(R.id.tv_content);
        if (content.length() != 0 && !"".equals(context))
            textView.setText(content);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.dimAmount = 0.5f;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }
}
