package com.example.javademo.view.eventdispatch;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javademo.R;
import com.example.javademo.adapter.EventSampleRvAdapter;
import com.example.javademo.adapter.ParentEventRvAdapter;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityEventConflict3Binding;
import com.example.javademo.databinding.ActivityEventConflict4Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * 纵向RecyclerView与纵向RecyclerView
 * 如果什么也不处理, 滑动时,如子Rv高度不够 , 则子Rv无法显示全且无法滑动 ,
 * 这时一种方案时给子Rv设置wrap_content ,使其每个子rv的item都显示全.
 * 或者重写子Rv 详情见 -> ChildRecyclerView
 *
 * 父Rv和子Rv同时设置onClickListener , 则父布局事件会完全被子布局处理
 */
public class EventConflict4Activity extends BaseActivity<ActivityEventConflict4Binding> {

    private ParentEventRvAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_conflict4;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ParentEventRvAdapter(this, R.layout.item_event_recyclerview);
        //添加Android自带的分割线
        mBinding.rvContent.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mBinding.rvContent.setAdapter(mAdapter);
        mAdapter.updateData(list,false);
    }
}
