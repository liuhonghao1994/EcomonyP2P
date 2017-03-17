package com.liuhonghao.com.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

public class RegesterActivity extends BaseActivity {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    public void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdagain = etRegisterPwdagain.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) ||
                        TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdagain)){
                    showToast("啥都不能为空！！！");
                    return;
                }
                Map<String,String> map=new HashMap<String, String>();
                map.put("name",name);
                map.put("password",pwd);
                map.put("phone",number);
                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {
                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");
                        if (isExist){
                            showToast("账号已经注册过");
                        }else{
                            showToast("注册成功");
                            finish();
                        }
                    }

                    @Override
                    public void fail(String error) {

                    }
                });
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        baseSetting.setVisibility(View.INVISIBLE);
        baseTitle.setText("注册");
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_regester;
    }


}
