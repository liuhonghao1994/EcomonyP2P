package com.liuhonghao.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.liuhonghao.viewHolder.BaseHolder;

import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public abstract class BaseAllAdapter3<T> extends BaseAdapter {
    private List<T> datas;

    public BaseAllAdapter3(List<T> data) {
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
        BaseHolder baseHolder=null;
        if (convertView == null) {

            baseHolder=getHolder();

        }else{
            baseHolder= (BaseHolder) convertView.getTag();
        }
        T t = datas.get(position);
        //调用传给baseholder的setChild()数据，而setChild()由实现baseholder的holder实现
        baseHolder.setData(t);

        return baseHolder.getView();
    }



    public abstract BaseHolder getHolder();
}
