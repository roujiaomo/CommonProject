package com.example.javademo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javademo.interfaces.MultipleType;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder> {

    public Context mContext;
    private int layoutId; //item布局(当多布局时 , 布局的type值为layoutId值)
    private LayoutInflater mLayoutInflater;
    private List<T> mList = new ArrayList<>();//数据源
    private MultipleType<T> multipleType;//多item布局使用,初始值null

    public BaseAdapter(Context mContext, int layoutId) {
        this.mContext = mContext;
        this.layoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(mContext);
        setHasStableIds(true); //防止数据重载时 图片闪烁
    }

    public BaseAdapter(Context mContext, MultipleType<T> multipleType) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.multipleType = multipleType;
        setHasStableIds(true); //防止数据重载时 图片闪烁
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (multipleType != null) { //不为null时 ,在getItemViewType()回调中赋值
            //多布局的viewType就是layoutId
            layoutId = viewType;
        }
        ViewDataBinding binding  = DataBindingUtil.inflate(mLayoutInflater, layoutId, parent, false);

        return new BaseViewHolder(binding , binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindData(holder, position, mList.get(position));
    }

    protected abstract void bindData(BaseViewHolder holder, int position, T t);


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public  ViewDataBinding viewDataBinding;
        public BaseViewHolder(ViewDataBinding viewDataBinding ,@NonNull View itemView) {
            super(itemView);
            this.viewDataBinding = viewDataBinding;
        }
    }

    /**
     * 刷新所有数据
     * @param newList
     * @param isClear 是否清除原有数据
     */
    public void updateData(List<T> newList , boolean isClear){
        if(isClear){
            mList.clear();
        }
        mList.addAll(newList);
        notifyDataSetChanged();
    }

    /**
     * 适配器中添加单条数据，刷新UI
     *
     * @param position 要添加的数据所在适配器中的位置
     * @param data     要添加的数据
     */
    public void insertItem(int position , T data) {
        mList.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 适配器中批量添加数据，刷新UI
     *
     * @param newList
     * @param positionStart 添加到适配器中的起始位置
     */
    public void insertItems(List<T> newList, int positionStart) {
        mList.addAll(newList);
        notifyItemRangeInserted(positionStart, newList.size());
    }


    /**
     * 适配器中删除单条数据，刷新UI
     *
     * @param position 要删除的数据所在适配器中的位置
     */
    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    /**
     * 适配器中批量删除多条数据，刷新UI
     *
     * @param newList         要删除的数据集合
     * @param positionStart 删除的数据在适配器中的起始位置
     */
    public void removeItems(List<T> newList, int positionStart) {
        mList.removeAll(newList);
        notifyItemRangeRemoved(positionStart, mList.size());
    }

    /**
     * 修改适配器中单条数据，刷新UI
     *
     * @param position 更新的数据所在适配器中的位置
     * @param data     更新后的数据集合
     */
    public void updateItem(int position, T data) {
        mList.remove(position);
        mList.add(position, data);
        notifyItemChanged(position);
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 多布局，用layoutId作为ItemViewType
     */
    @Override
    public int getItemViewType(int position) {
        if(null != multipleType){
            return   multipleType.getLayoutId(mList.get(position), position);
        }else {
            return super.getItemViewType(position);
        }
    }
}
