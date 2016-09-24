package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyApplication extends Application {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;
    private static String mIMEI;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mainThreadId = android.os.Process.myTid();// 获取当前主线程id
        handler = new Handler();
        mIMEI = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.d("MyApplication","IMEI:"+mIMEI);

    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static String getIMEI(){
        return mIMEI;
    }
}
