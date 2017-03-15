package com.liuhonghao.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 刘红豪 on 2017/3/14.
 */

public abstract class BaseAllAdapter<T> extends BaseAdapter {
    private List<T> datas;

    public BaseAllAdapter(List<T> data) {
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
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = initView();
            viewHolder=new ViewHolder(convertView);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        T t = datas.get(position);
        setData(t,convertView);

        return convertView;
    }

    protected abstract void setData(T t, View view);

    public abstract View initView();

     class ViewHolder {


        ViewHolder(View view) {
            view.setTag(this);
        }
    }
}
