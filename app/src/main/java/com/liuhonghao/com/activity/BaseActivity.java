package com.liuhonghao.com.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.liuhonghao.com.bean.DataBean;
import com.liuhonghao.com.bean.UserInfo;

import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.bind(this);
        initTitle();
        initData();
        initListener();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initTitle();

    public abstract int getLayoutid();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    //弹出吐司
    public void showToast(String context){
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }
    public void saveUser(UserInfo userInfo){
                SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("imageurl",userInfo.getData().getImageurl());
                edit.putString("iscredit",userInfo.getData().getIscredit());
                edit.putString("name",userInfo.getData().getName());
                edit.putString("phone",userInfo.getData().getPhone());
               edit.commit();
            }
    public UserInfo getUser(){
                SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
               String imageurl = sp.getString("imageurl", "");
                String iscredit = sp.getString("iscredit", "");
                String name = sp.getString("name", "");
                String phone = sp.getString("phone", "");
                UserInfo userInfo = new UserInfo();
                DataBean dataBean = new DataBean();
                dataBean.setImageurl(imageurl);
                dataBean.setIscredit(iscredit);
                dataBean.setName(name);
                dataBean.setPhone(phone);
                userInfo.setData(dataBean);
               return userInfo;
            }
}
