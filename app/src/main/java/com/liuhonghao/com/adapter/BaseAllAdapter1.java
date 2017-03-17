package com.liuhonghao.com.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public abstract class BaseAllAdapter1<T> extends BaseAdapter {
    private List<T> datas;

    public BaseAllAdapter1(List<T> data) {
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       View view=getChildView(position,convertView,viewGroup);
        return  view;
    }

    public abstract View getChildView(int position, View convertView, ViewGroup viewGroup);


}
