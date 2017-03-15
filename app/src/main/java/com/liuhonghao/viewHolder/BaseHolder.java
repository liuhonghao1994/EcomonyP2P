package com.liuhonghao.viewHolder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 刘红豪 on 2017/3/14.
 */
//谁实现我谁去设置我的convertView和
public abstract class BaseHolder<T>{
    private final View rootView;

    public BaseHolder(){
        rootView=initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }
    public View getView(){
           return rootView;
    }
    private T t;
    public void setData(T t) {
        this.t = t;
        setChildData();
    }

    public T getT() {
        return t;
    }

    public abstract View initView();
    public abstract void setChildData();
}
