package com.liuhonghao.com.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.InvestAllBean;
import com.liuhonghao.com.ecomonyp2p.ui.MyProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestAllAdapter11 extends BaseAllAdapter1<InvestAllBean.DataBean> {


    private final List<InvestAllBean.DataBean> datas;

    public InvestAllAdapter11(List<InvestAllBean.DataBean> data) {
        super(data);
        this.datas=data;
    }

    @Override
    public View getChildView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(viewGroup.getContext(), R.layout.adapter_invest_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InvestAllBean.DataBean dataBean = datas.get(position);
        viewHolder.pName.setText(dataBean.getName());
        return convertView;
    }
    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
