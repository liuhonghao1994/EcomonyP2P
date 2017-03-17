package com.liuhonghao.com.adapter;

import android.view.View;
import android.widget.TextView;

import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.InvestAllBean;
import com.liuhonghao.com.ecomonyp2p.utils.UIUtils;

import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestAllAdapter1 extends BaseAllAdapter<InvestAllBean.DataBean> {


    public InvestAllAdapter1(List<InvestAllBean.DataBean> data) {
        super(data);

    }


    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View view) {
        TextView textView = (TextView) view.findViewById(R.id.p_name);
        textView.setText(dataBean.getName());
    }

    @Override
    public View initView() {
        return UIUtils.getView(R.layout.adapter_invest_all);
    }



}
