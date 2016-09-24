package com.example.administrator.myapplication.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.administrator.myapplication.MyApplication;

/**
 * Created by psq on 2016/9/24
 */
public class InitUtil {
    private static String mIMEI;

    public static String getIMEI (){
        mIMEI = ((TelephonyManager) MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.d("MyApplication","IMEI:"+mIMEI);
        return mIMEI;
    }
}
