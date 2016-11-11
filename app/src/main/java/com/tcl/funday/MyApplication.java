package com.tcl.funday;

import android.app.Application;
import android.content.Context;

import com.tcl.funday.utils.Logger;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/8 20:09
 * @copyright HAWK
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 打开Debug日志
        Logger.DEBUG = BuildConfig.LOG_DEBUG;
    }

    public static Context getContext() {
        return mContext;
    }
}
