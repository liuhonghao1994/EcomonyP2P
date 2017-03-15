package com.liuhonghao.com.ecomonyp2p.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 刘红豪 on 2017/3/11.
 */
//线程池
public class ThreadPool {
    private ThreadPool(){};
    private static ThreadPool threadPool=new ThreadPool();
    public  static ThreadPool getinstance(){
        return  threadPool;
    }
    private ExecutorService executorService= Executors.newCachedThreadPool();
    public ExecutorService getservice(){
        return  executorService;
    }
}
