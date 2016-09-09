package com.example.administrator.myapplication;

import android.os.SystemClock;

/**
 * Created by psq on 2016/9/8
 */
public class ScriptSet {


    //使用accessibility 监听点击事件，获取当前窗口的className判断是不是已经点击了按钮。???指令模拟点击没有日志
    public static void WXAddFriendScript(String wxnum, String remark, String screenname) {
        if (findAddImg(screenname)) {
            if (clickAdd(screenname)) {
                if (clickAddFriend(screenname)) {
                    if (clicklvsefangdajing(screenname)) {
                        if (findsousuobut(screenname, wxnum)) {
                            if (clicksousuo(screenname)) { //账号为数字，跳详细资料页面。账号为字母+数字跳搜索页面，要再点一层才到详细资料页面。
                                new RootShellCmd().simulateClick(340, 213);
                            }
                        }

                    }
                }
            }
        }
    }


    private static boolean findAddImg(String screenname) {
        SystemClock.sleep(4000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(1000);
        int a = new RootShellCmd().getColors(638, 98);   //判断加号位置 638 98 , 651 83 ,666 98 , 656 93 黑色 r=57  g=58   b=63
        int b = new RootShellCmd().getColors(651, 83);
        int c = new RootShellCmd().getColors(666, 98);
        int d = new RootShellCmd().getColors(656, 93);
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            return true;                                    //     1280*720 点击+号坐标   r=57  g=58   b=63
        } else {
            return false;
        }
    }

    public static boolean clickAdd(String screenname) {
        new RootShellCmd().simulateClick(715, 100);//找到+号，点击
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(318, 164);   //r=57  g=58   b=63
        int b = new RootShellCmd().getColors(666, 600);
        int c = new RootShellCmd().getColors(622, 190);
        int d = new RootShellCmd().getColors(347, 300);
        if (a == -13026753 && b == -13026753 && c == -13026753 && d == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean clickAddFriend(String screenname) {
        new RootShellCmd().simulateClick(600, 320);//666 310     1280*720 添加朋友坐标  r=57  g=58   b=63
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);  //点了添加朋友截图（绿色放大镜界面）
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(44, 235);   //63 184 56 绿
        int b = new RootShellCmd().getColors(74, 236);   //绿
        int c = new RootShellCmd().getColors(80, 256);   //绿
        if (a == -12601288 && b == -12601288 && c == -12601288) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean clicklvsefangdajing(String screenname) {
        new RootShellCmd().simulateClick(240, 240);             // 添加朋友界面，点击放大镜
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //白色放大镜
        int a = new RootShellCmd().getColors(135, 85);   //白
        int b = new RootShellCmd().getColors(162, 110);//白
        int c = new RootShellCmd().getColors(144, 92);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            return true;
        } else {
            return false;
        }
    }

    //找到输入微信账号后出现的搜索按钮
    public static boolean findsousuobut(String screenname, String wxnum) {
        new RootShellCmd().setText(wxnum);
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //搜索微信账号，绿框包白色放大镜
        int a = new RootShellCmd().getColors(38, 173);   //深绿  43 162 69
        int b = new RootShellCmd().getColors(109, 242);//深绿
        int c = new RootShellCmd().getColors(81, 216);//白
        if (a == -13917627 && b == -13917627 && c == -1) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean clicksousuo(String screenname) {
        new RootShellCmd().simulateClick(340, 213);//搜索微信账号按钮
        //判断网络
        SystemClock.sleep(3000);

        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);

        int a = new RootShellCmd().getColors(671,82);   //白
        int b = new RootShellCmd().getColors(671,98);//白
        int c = new RootShellCmd().getColors(672,90);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            return true;
        }
        return false;
    }


}

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