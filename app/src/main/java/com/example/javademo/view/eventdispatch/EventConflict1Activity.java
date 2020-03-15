package com.example.javademo.view.eventdispatch;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javademo.R;
import com.example.javademo.adapter.EventSampleRvAdapter;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityEventConflict1Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * 父布局HorizontalScrollView与纵向RecyclerView
 * 无冲突
 */
public class EventConflict1Activity extends BaseActivity<ActivityEventConflict1Binding> {

    private EventSampleRvAdapter adapter1, adapter2, adapter3, adapter4, adapter5;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_conflict1;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rv1.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rv2.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rv3.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rv4.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rv5.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rv1.setAdapter(adapter1);
        adapter2 = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rv2.setAdapter(adapter2);
        adapter3 = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rv3.setAdapter(adapter3);
        adapter4 = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rv4.setAdapter(adapter4);
        adapter5 = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rv5.setAdapter(adapter5);
        adapter1.updateData(list,false);
        adapter2.updateData(list,false);
        adapter3.updateData(list,false);
        adapter4.updateData(list,false);
        adapter5.updateData(list,false);
    }
}
