package com.liuhonghao.com.ecomonyp2p.utils;

import android.os.Looper;
import android.widget.Toast;

import com.liuhonghao.com.ecomonyp2p.command.AppManager;

/**
 * Created by 刘红豪 on 2017/3/11.
 */

public class Crashhandleer implements  Thread.UncaughtExceptionHandler {
    private Crashhandleer(){};
    private  static  Crashhandleer crashhandleer=new Crashhandleer();
    public  static  Crashhandleer getinstance(){
        return  crashhandleer;


    }
    public  void  init(){
        //当前类设置成默认处理未捕获异常，异常自己处理，不交给系统处理
       Thread.setDefaultUncaughtExceptionHandler(this);
    }
    //有异常的处理方法
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //有异常销毁所有Activity
        AppManager.getInstance().removeAll();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(UIUtils.getContext(), "异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
                //两个looper中间的也是主线程。
            }
        }.start();
        //收集信息
        collection(throwable);
        //结束当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //关闭虚拟机,0为正常退出，其他为非正常。
        System.exit(0);
    }

    private void collection(Throwable throwable) {
        //执行崩溃信息，返回服务器
        /*Build.BOARD // 主板  
        Build.BRAND // android系统定制商  
        Build.CPU_ABI // cpu指令集  
        Build.DEVICE // 设备参数  
        Build.DISPLAY // 显示屏参数  
        Build.FINGERPRINT // 硬件名称  
        Build.HOST  
        Build.ID // 修订版本列表  
        Build.MANUFACTURER // 硬件制造商  
        Build.MODEL // 版本  
        Build.PRODUCT // 手机制造商  
        Build.TAGS // 描述build的标签  
        Build.TIME  
// 当前开发代号  
        Build.VERSION.CODENAME  
// 源码控制版本号  
        Build.VERSION.INCREMENTAL  
// 版本字符串  
        Build.VERSION.RELEASE  
// 版本号  
        Build.VERSION.SDK  
// 版本号  
        Build.VERSION.SDK_INT  
        Build.TYPE // builder类型  
        Build.USER */

    }
}
