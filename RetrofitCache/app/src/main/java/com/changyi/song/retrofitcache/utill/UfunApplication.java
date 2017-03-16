package com.changyi.song.retrofitcache.utill;

import android.app.Application;
import android.content.Context;


/**
 * Created by jyh on 2016/5/28.
 */
public class UfunApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
     /*   初始化日志采集器*/
    }
    /**全局上下文*/
    public static Context getContext(){
        return  mContext;
    }
}
