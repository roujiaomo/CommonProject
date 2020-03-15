package com.example.javademo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.javademo.base.BaseAdapter;
import com.example.javademo.databinding.ItemEventSampleBinding;

public class ChildEventRvAdapter extends BaseAdapter<String> {
    private static final String TAG = "ChildEventRvAdapter";
    public ChildEventRvAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, final int position, String s) {
        ( (ItemEventSampleBinding)holder.viewDataBinding).tvContent.setText(s);
        ( (ItemEventSampleBinding)holder.viewDataBinding).tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了子布局 位置:" + position);
            }
        });
    }
}
