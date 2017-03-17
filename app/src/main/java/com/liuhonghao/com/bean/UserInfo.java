package com.liuhonghao.com.bean;

/**
 * Created by 刘红豪 on 2017/3/15.
 */

public class UserInfo {
    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
