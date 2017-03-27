package com.dddemo.sumanthmadala.androidapp.dddemo;

import android.app.Application;

import com.dddemo.sumanthmadala.androidapp.dddemo.net.NetUtils;

/**
 * Created by sumanthmadala on 3/25/17.
 */

public class DDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetUtils.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
