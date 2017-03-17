package com.liuhonghao.com.ecomonyp2p;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuhonghao.com.activity.BaseActivity;
import com.liuhonghao.com.activity.LoginActivity;
import com.liuhonghao.com.bean.UserInfo;
import com.liuhonghao.com.ecomonyp2p.command.AppManager;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.pb_bar)
    ProgressBar pbBar;
    @Bind(R.id.tv_banben)
    TextView tvBanben;



    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        AppManager.getInstance().addActivity(this);
        //启动动画
        showAnimation();
        setVersion();
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
    }

    private void setVersion() {
        tvBanben.setText(getVersion());

    }

    private String getVersion() {
        //得到包管理器
        PackageManager packageManager=getPackageManager();
        PackageInfo packageInfo= null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(),0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return "";
    }

    private void showAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isLog()){
                    //登录过进入主界面
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //启动动画
        ivWelcomeIcon.startAnimation(alphaAnimation);

    }

    private boolean isLog() {
        UserInfo user = getUser();
        String name = user.getData().getName();
        if(TextUtils.isEmpty(name)){
                return  false;
        }


        return true;
    }


}
