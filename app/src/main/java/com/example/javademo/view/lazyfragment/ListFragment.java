package com.example.javademo.view.lazyfragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javademo.R;
import com.example.javademo.adapter.EventSampleRvAdapter;
import com.example.javademo.base.BaseLazyFragment;
import com.example.javademo.databinding.FragmentListBinding;
import com.example.javademo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表Fragment
 */

public class ListFragment extends BaseLazyFragment<MainViewModel, FragmentListBinding> {

    private static final String TAG = "ListFragment";
    private EventSampleRvAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new EventSampleRvAdapter(mContext, R.layout.item_event_sample);
        mBinding.rvContent.setAdapter(mAdapter);
        mAdapter.updateData(list,false);
    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public boolean isReload() {
        return false;
    }
}
