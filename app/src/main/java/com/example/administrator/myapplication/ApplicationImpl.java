package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by D on 2016/9/2.
 */
public class ApplicationImpl extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
