package com.example.javademo.view.event_dispatch;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.javademo.R;
import com.example.javademo.adapter.BaseFragmentPagerAdapter;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityEventConflict2Binding;
import com.example.javademo.view.lazyfragment.HomeFragment;
import com.example.javademo.view.lazyfragment.ListFragment;
import com.example.javademo.view.lazyfragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 父布局横向滑动平ViewPager与纵向RecyclerView(例主页)
 * 无冲突
 */
public class EventConflict2Activity extends BaseActivity<ActivityEventConflict2Binding> {

    private List<String> tabTitle ; //tab标题栏
    private List<Fragment> mFragments; //Vp切换的Fragment集合
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private MineFragment mineFragment;
    private BaseFragmentPagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_conflict2;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        listFragment = new ListFragment();
        mineFragment = new MineFragment();
        mFragments.add(homeFragment);
        mFragments.add(listFragment);
        mFragments.add(mineFragment);
        tabTitle = new ArrayList<>();
        tabTitle.add("首页");
        tabTitle.add("列表");
        tabTitle.add("我的");
        // 懒加载第二个参数要传   FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        mAdapter  = new BaseFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                mFragments ,tabTitle);
        mBinding.vpTabs.setAdapter(mAdapter);
        mBinding.vpTabs.setOffscreenPageLimit(mFragments.size()-1);
        mBinding.tlTabs.setupWithViewPager(mBinding.vpTabs);
    }
}
