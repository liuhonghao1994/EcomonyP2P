package com.liuhonghao.com.ecomonyp2p.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.liuhonghao.adapter.InvestAllAdapter3;
import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;
import com.liuhonghao.com.ecomonyp2p.base.InvestAllBean;
import com.liuhonghao.com.ecomonyp2p.command.AppNetConfig;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initData(String json) {
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);
        List<InvestAllBean.DataBean> data = investAllBean.getData();
       /* investAllLv.setAdapter(new InvestAllAdapter(data));*/
       /* investAllLv.setAdapter(new InvestAllAdapter1(data));*/
       /* investAllLv.setAdapter(new InvestAllAdapter11(data));*/
        investAllLv.setAdapter(new InvestAllAdapter3(data));
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
