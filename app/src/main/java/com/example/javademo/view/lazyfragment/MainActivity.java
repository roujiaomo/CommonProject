package com.example.javademo.view.lazyfragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.javademo.R;
import com.example.javademo.adapter.BaseFragmentPagerAdapter;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityMainBinding;
import com.example.javademo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    private List<Fragment> mFragments;
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private MineFragment mineFragment;
    private BaseFragmentPagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(ActivityMainBinding bindView) {
        mBinding.tlTabs.addTab(mBinding.tlTabs.newTab().setText("首页"));
        mBinding.tlTabs.addTab(mBinding.tlTabs.newTab().setText("列表"));
        mBinding.tlTabs.addTab(mBinding.tlTabs.newTab().setText("我的"));
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
        // 懒加载第二个参数要传   FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        mAdapter  = new BaseFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                mFragments);
        mBinding.vpTabs.setAdapter(mAdapter);
        mBinding.vpTabs.setOffscreenPageLimit(mFragments.size()-1);
        mBinding.tlTabs.setupWithViewPager(mBinding.vpTabs);
    }

    @Override
    public void initLiveDataObserve() {

    }
}
