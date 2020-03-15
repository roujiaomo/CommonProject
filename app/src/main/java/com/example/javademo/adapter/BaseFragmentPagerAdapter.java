package com.example.javademo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> title;
    /**
     *
     * @param fm
     * @param behavior  懒加载第二个参数要传  FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
     * @param fragments viewPager切换的Fragment集合
     * @param title tabLayout的标题集合
     */
    public BaseFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior,
                                    List<Fragment> fragments , List<String> title) {
        super(fm, behavior);
        this.fragments = fragments;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
