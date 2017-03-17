package com.liuhonghao.com.ecomonyp2p.fragment;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuhonghao.com.bean.UserInfo;
import com.liuhonghao.com.ecomonyp2p.MainActivity;
import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;
import com.liuhonghao.com.ecomonyp2p.command.AppNetConfig;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by 刘红豪 on 2017/3/10.
 */

public class PropertityFragment extends BaseFragment {
    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;
    private TextView textView;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_property;

    }

    @Override
    protected void initData(String json) {
        MainActivity activity = (MainActivity) getActivity();
        UserInfo user = activity.getUser();
        tvMeName.setText(user.getData().getName());
       /* Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {

                        Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);

                        bitmap.recycle(); //必须把原来的释放掉
                                        return circleBitmap;
                                    }

                    @Override
                    public String key() {
                        return ""; //不能为空否则会报错
                    }
                })
                .into(ivMeIcon);*/
        Picasso.with(getActivity()).load(AppNetConfig.BASE_URL+"/images/tx.png")
                                .transform(new CropCircleTransformation())
                                .transform(
                                        new ColorFilterTransformation(Color
                                                .parseColor("#66FFccff")))
                        //第二个参数值越大越模糊
                .transform(new BlurTransformation(getActivity(),8))
                .into(ivMeIcon);

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
