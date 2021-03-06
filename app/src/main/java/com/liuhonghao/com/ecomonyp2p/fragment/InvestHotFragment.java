package com.liuhonghao.com.ecomonyp2p.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public class InvestHotFragment extends BaseFragment {

    @Bind(R.id.ivest_hot_fl)
    TagFlowLayout ivestHotFl;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
         };
    private Random random = new Random();
    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_hot;
    }

    @Override
    protected void initData(String json) {
        ivestHotFl.setAdapter(new TagAdapter<String>(datas) {


            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(getActivity());
                tv.setText(s);
                tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.hot_shape));
                //得到tv的selector中的东西
                GradientDrawable drawable = (GradientDrawable) tv.getBackground();
                int red = random.nextInt(200-50)+50;
                int green = random.nextInt(211);
                int blue = random.nextInt(211);
                drawable.setColor(Color.rgb(red,green,blue));
                return tv;
            }
        });

    }

    @Override
    public String getChildUrl() {
        return null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
