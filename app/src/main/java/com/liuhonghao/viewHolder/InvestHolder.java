package com.liuhonghao.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.InvestAllBean;
import com.liuhonghao.com.ecomonyp2p.ui.MyProgress;
import com.liuhonghao.com.ecomonyp2p.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestHolder extends BaseHolder<InvestAllBean.DataBean> {
    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    MyProgress pProgresss;

    @Override
    public View initView() {
        return UIUtils.getView(R.layout.adapter_invest_all);

    }

    @Override
    public void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pMinnum.setText(dataBean.getMinTouMoney());
        pMinzouzi.setText(dataBean.getMemberNum());
    }
}
