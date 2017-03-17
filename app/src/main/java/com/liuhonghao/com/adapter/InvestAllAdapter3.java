package com.liuhonghao.com.adapter;

import com.liuhonghao.com.ecomonyp2p.base.InvestAllBean;
import com.liuhonghao.viewHolder.BaseHolder;
import com.liuhonghao.viewHolder.InvestHolder;

import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestAllAdapter3 extends BaseAllAdapter3<InvestAllBean.DataBean> {

    public InvestAllAdapter3(List<InvestAllBean.DataBean> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder() {
        return new InvestHolder();
    }


}
