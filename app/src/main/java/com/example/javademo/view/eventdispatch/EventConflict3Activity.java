package com.example.javademo.view.eventdispatch;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javademo.R;
import com.example.javademo.adapter.EventSampleRvAdapter;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityEventConflict3Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * 父布局纵向ScrollView与纵向RecyclerView
 * 使用NestedScrollView则无冲突
 */
public class EventConflict3Activity extends BaseActivity<ActivityEventConflict3Binding> {

    private EventSampleRvAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_conflict3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rvContent.setAdapter(mAdapter);
        mAdapter.updateData(list,false);
    }
}
