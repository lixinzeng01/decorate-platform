package com.lixz.hd;

import android.app.Application;


public class MyApplication extends Application{
    public static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication=this;

    }

    public static MyApplication getApp(){
        return mMyApplication;
    }
}
