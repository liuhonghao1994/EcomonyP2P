package com.liuhonghao.com.ecomonyp2p.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhonghao.adapter.InvesAdapter;
import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.invest_vp)
    ViewPager investVp;
    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String json) {
        //设置标题
        initTitle();
        //初始化Fragmet
        initFragments();
        //初始化viewPager
        initViewPager();
        initTab();
        initListener();
    }

    private void initListener() {
        investVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //一下三个是当tab选中的时候viewpager也发生改变
        tvInvestAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                investVp.setCurrentItem(0);
            }
        });
        tvInvestRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });
        tvInvestHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              investVp.setCurrentItem(2);
            }
                    });
    }



    private void initTab() {
        selectText(0);
    }
    //其实这个就是当viewpager改变的时候选择tab标签颜色
    private void selectText(int id) {
        //把所有的背景色还原成默认值
        hiddenTextViewState();
        switch (id){
            case 0:
                  //改变当前的背景色
             tvInvestAll.setBackgroundColor(Color.RED);
              break;
            case 1:
             tvInvestRecommend.setBackgroundColor(Color.RED);
             break;
            case 2:
             tvInvestHot.setBackgroundColor(Color.RED);
             break;
        }
    }
    //默认全是白色
    private void hiddenTextViewState() {
        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setBackgroundColor(Color.WHITE);
    }

    private void initViewPager() {
        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), fragments));
    }

    private void initFragments() {
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());
    }

    private void initTitle() {
        baseSetting.setVisibility(View.GONE);
        baseTitle.setText("投资");
        baseBack.setVisibility(View.GONE);
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
