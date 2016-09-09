package com.example.administrator.myapplication;

import android.os.SystemClock;

/**
 * Created by psq on 2016/9/8
 */
public class ScriptSet {

    //使用accessibility 监听点击事件，获取当前窗口的className判断是不是已经点击了按钮。???指令模拟点击没有日志
    public static void WXAddFriendScript(String wxnum, String remark, String screenname) {
        // RootShellCmd cmd = new RootShellCmd();

       /* new RootShellCmd().simulateSwipeLeftRight(100, 600);
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
        SystemClock.sleep(2500);
        new RootShellCmd().simulateSwipeUpDown(900, 100);
        SystemClock.sleep(2000);
        new RootShellCmd().simulateClick(500, 130);*/

        SystemClock.sleep(5000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int colors = new RootShellCmd().getColors(715, 100);
        if (colors == -13026753) {
            new RootShellCmd().simulateClick(715, 100); //     1280*720 +号坐标   r=57  g=58   b=63
        } else{


        }


        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int colors1 = new RootShellCmd().getColors(666, 310);
        if (colors1 == -13026753) {
            new RootShellCmd().simulateClick(600, 320); //666 310     1280*720 添加朋友坐标  r=57  g=58   b=63

        }


        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int colors2 = new RootShellCmd().getColors(301, 269);
        if (colors2 == -12206054) {
            new RootShellCmd().simulateClick(240, 240);             // 添加朋友输入框绿线  69 192 26

        }

/*
        SystemClock.sleep(1000);
        int colors2 = new RootShellCmd().getColors(301, 269);
        if(colors2==-1){

        }
        new RootShellCmd().simulateClick(240, 240); //301,269
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
        new RootShellCmd().simulateKey(4);*/
    }


}
