package com.liuhonghao.com.ecomonyp2p.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by 刘红豪 on 2017/3/11.
 */

public class LoadNet {
    public static  void  getDataPost(String url, final LoadNetHttp loadNetHttp){
        AsyncHttpClient httpClient=new AsyncHttpClient();
        httpClient.post(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if(loadNetHttp!=null){
                    loadNetHttp.success(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if(loadNetHttp!=null){
                    loadNetHttp.fail(content);
                }
            }
        });
    }

}
