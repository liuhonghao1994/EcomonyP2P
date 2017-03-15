package com.liuhonghao.com.ecomonyp2p.fragment;

import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestRecommendFragment extends BaseFragment{
    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initData(String json) {

    }

    @Override
    public String getChildUrl() {
        return null;
    }
}
