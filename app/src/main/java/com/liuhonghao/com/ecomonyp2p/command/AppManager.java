package com.liuhonghao.com.ecomonyp2p.command;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 刘红豪 on 2017/3/10.
 */
    //Activity的管理栈
public class AppManager {
    private AppManager(){};

    private static AppManager manager= new AppManager();

    public static AppManager getInstance(){
       return manager;
       }

   private static Stack<Activity> stack= new Stack<>();

    public void addActivity(Activity activity){

        if (activity != null){
            stack.add(activity);
             }
         }

    public void removeActivity(Activity activity){

        if (activity != null) {
         for (int i = stack.size()-1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if (currentActivity
                 ==(activity)){
                     //currentActivity.finish();系统已经销毁，不用再执行finish（）；
                    stack.remove(currentActivity);
                  }
                 }
           }
        }

   public void removeAll(){

        for (int i = stack.size()-1; i >=0; i--) {

            Activity currentActivity = stack.get(i);
            if (currentActivity != null){
                currentActivity.finish();
                stack.remove(currentActivity);
               }

             }
         }
    public void removeCurrentActivity(){
        Activity activity = stack.get(stack.size()-1);
        activity.finish();
        stack.remove(activity);
    }
    //返回栈大小
    public int getStackSize(){
        return stack.size();
    }

}
