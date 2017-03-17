package com.liuhonghao.com.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhonghao.com.bean.UserInfo;
import com.liuhonghao.com.ecomonyp2p.MainActivity;
import com.liuhonghao.com.ecomonyp2p.R;
import com.liuhonghao.com.ecomonyp2p.command.AppNetConfig;
import com.liuhonghao.com.ecomonyp2p.utils.LoadNet;
import com.liuhonghao.com.ecomonyp2p.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by 刘红豪 on 2017/3/15.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_login_number)
    TextView tvLoginNumber;
    @Bind(R.id.login_et_number)
    EditText loginEtNumber;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.login_et_pwd)
    EditText loginEtPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.login_regitster_tv)
    TextView loginRegitsterTv;

    @Override
    public void initListener() {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });
        loginRegitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegesterActivity.class));
            }
        });
    }
    private void login(){
        String number = loginEtNumber.getText().toString().trim();
        String pw = loginEtPwd.getText().toString().trim();
        if(TextUtils.isEmpty(number)){
            showToast("账号不能为空");
        }
        if(TextUtils.isEmpty(pw)){
            showToast("密码不能为空");
        }
        //利用键值对存贮到服务器
        Map<String,String> map=new HashMap<String, String>();
        map.put("phone",number);
        map.put("password",pw);
        //去服务器登录
        LoadNet.getDataPost(AppNetConfig.LOGIN, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                JSONObject jsonObject = JSON.parseObject(context);
                Boolean success = jsonObject.getBoolean("success");
                Log.e("TAG", "11111111111"+success);
                if (success){
                //解析数据
                 UserInfo userInfo = JSON.parseObject(context, UserInfo.class);
                //保存数据到sp
                 saveUser(userInfo);
                 //跳转
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                //结束当前页面
                finish();
               }else{
              showToast("账号不存在或者密码错误");
              }
            }

            @Override
            public void fail(String error) {
                Log.e("login", "error: "+error);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
        baseTitle.setText("登录");
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }


}
