package com.liuhonghao.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvesAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> fragments = new ArrayList<>();
    public InvesAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
