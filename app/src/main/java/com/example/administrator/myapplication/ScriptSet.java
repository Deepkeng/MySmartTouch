package com.example.administrator.myapplication;

import android.os.SystemClock;

/**
 * Created by psq on 2016/9/8
 */
public class ScriptSet {


    public static void WXScript(String wxnum,String remark,String screenname) {
        // RootShellCmd cmd = new RootShellCmd();
        new RootShellCmd().simulateSwipeLeftRight(100, 600);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateSwipeLeftRight(600, 100);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateSwipeUpDown(100, 500);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateSwipeUpDown(900, 300);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(26);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(26);
        SystemClock.sleep(2000);
        new RootShellCmd().simulateSwipeUpDown(900, 300);
        SystemClock.sleep(2000);
        new RootShellCmd().simulateClick(500, 130);
        SystemClock.sleep(2000);
        new RootShellCmd().simulateClick(715, 100);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateClick(600, 320);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateClick(240, 240);
        SystemClock.sleep(1000);
        new RootShellCmd().setText(wxnum);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateClick(240, 240);
        SystemClock.sleep(3000);
        new RootShellCmd().simulateClick(715, 100);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateClick(550, 200);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(67);
        new RootShellCmd().simulateKey(67);
        new RootShellCmd().simulateKey(67);
        new RootShellCmd().simulateKey(67);
        new RootShellCmd().simulateKey(67);
        SystemClock.sleep(1000);
        new RootShellCmd().setText(remark);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateClick(715, 100);
        SystemClock.sleep(1000);
        new RootShellCmd().getScreen(screenname);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
    }


}
