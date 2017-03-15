package com.liuhonghao.com.ecomonyp2p;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuhonghao.com.ecomonyp2p.command.AppManager;
import com.liuhonghao.com.ecomonyp2p.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.pb_bar)
    ProgressBar pbBar;
    @Bind(R.id.tv_banben)
    TextView tvBanben;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        initData();
    }


    private void initData() {

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "UIUtils.getHandler()==" + UIUtils.getHandler().toString());
                pbBar.setVisibility(View.GONE);
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();

            }
        }, 3000);

        //启动动画
        showAnimation();
        setVersion();
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

        //启动动画
        ivWelcomeIcon.startAnimation(alphaAnimation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //AppManager.getInstance().removeCurrentActivity();
        UIUtils.getHandler().removeCallbacksAndMessages(null);
        AppManager.getInstance().removeActivity(this);
    }
}
