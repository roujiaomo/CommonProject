package com.example.javademo.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.javademo.R;
import com.example.javademo.base.BaseAdapter;
import com.example.javademo.bean.NewsBean;
import com.example.javademo.databinding.ItemNewsBinding;

/**
 * 普通adapter
 */
public class NewsAdapter extends BaseAdapter<NewsBean> {


    public NewsAdapter(Context mContext,   int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, NewsBean newsBean) {
//        ItemNewsBinding viewDataBinding = (ItemNewsBinding) holder.viewDataBinding;
//        viewDataBinding.tvInfo
    }
}
