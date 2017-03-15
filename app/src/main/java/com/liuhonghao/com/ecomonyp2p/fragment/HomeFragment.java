package com.liuhonghao.com.ecomonyp2p.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.liuhonghao.bean.HomeBean;
import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;
import com.liuhonghao.com.ecomonyp2p.command.AppNetConfig;
import com.liuhonghao.com.ecomonyp2p.ui.MyProgress;
import com.liuhonghao.com.ecomonyp2p.utils.ThreadPool;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 刘红豪 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.home_progress)
    MyProgress homeProgress;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;

   /* private void initListener() {
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }*/

    public void initData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        //Log.i("http", "success: "+homeBean.getImageArr().size());
        tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() + "%");
        tvHomeProduct.setText(homeBean.getProInfo().getName());
        //注意：展示UI一定要判断是不是主线程
        initProgress(homeBean.getProInfo());
        initBanner(homeBean);

    }

    private void initProgress(final HomeBean.ProInfoBean proInfo) {
        ThreadPool.getinstance().getservice().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <= progress; i++) {
                    SystemClock.sleep(100);
                    homeProgress.setProgress(i);
                }
            }
        });
    }

    private void initBanner(HomeBean homeBean) {
        banner.setImageLoader(new GlideImageLoader());
        //图片网址集合
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
        }
        //设置图片集合
        banner.setImages(urls);
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        String[] titles = new String[]{"分享返学费1111元", "人脉总动员", "想不到你是这样的APP", "11月兑物节"};
        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
//设置轮播时间
        banner.setDelayTime(1500);
//设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @Override
    public int getLayoutid() {
        Logger.xml(String.valueOf(R.layout.fragment_home));
        return R.layout.fragment_home;
    }


    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */


            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

    }


}
