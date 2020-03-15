package com.example.javademo.adapter;

import android.content.Context;

import com.example.javademo.base.BaseAdapter;
import com.example.javademo.databinding.ItemEventSampleBinding;
import com.example.javademo.interfaces.MultipleType;

public class EventSampleRvAdapter extends BaseAdapter<String> {

    public EventSampleRvAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, String s) {
        ( (ItemEventSampleBinding)holder.viewDataBinding).tvContent.setText(s);
    }
}
