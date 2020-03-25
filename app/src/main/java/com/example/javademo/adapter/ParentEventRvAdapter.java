package com.example.javademo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javademo.R;
import com.example.javademo.base.BaseAdapter;
import com.example.javademo.databinding.ItemEventRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * RV嵌套RV 外层Adapter
 */
public class ParentEventRvAdapter extends BaseAdapter<String> {
    private static final String TAG = "ParentEventRvAdapter";
//    private ChildEventRvAdapter childEventRvAdapter;
    private List<String> childList = new ArrayList<>();
    private LinearLayoutManager childGridLayoutManager;

    public ParentEventRvAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
        for (int i = 0; i < 6; i++) {
            childList.add("第" + i + "条");
        }
    }

    @Override
    protected void bindData(BaseViewHolder holder,final int position, String s) {
        //操作每个父布局item里的Rv
        childGridLayoutManager = new LinearLayoutManager(mContext);
        ( (ItemEventRecyclerviewBinding)holder.viewDataBinding).rvItem.
                setLayoutManager(childGridLayoutManager);
        ChildEventRvAdapter childEventRvAdapter = new ChildEventRvAdapter(mContext, R.layout.item_event_sample);
        ( (ItemEventRecyclerviewBinding)holder.viewDataBinding).rvItem.
                setAdapter(childEventRvAdapter);
        childEventRvAdapter.updateData(childList ,true);
        ( (ItemEventRecyclerviewBinding)holder.viewDataBinding).llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了父布局 位置: " + position);
            }
        });
    }
}
