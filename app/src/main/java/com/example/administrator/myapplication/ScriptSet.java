package com.example.administrator.myapplication;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by psq on 2016/9/8
 */
public class ScriptSet {
    private static final String TAG = "ScriptSet";


    /**
     * 执行微信添加朋友备注脚本
     *
     * @param weixinaccount 要添加的账号
     * @param screenname    截图保存的图片名称
     * @return 是否添加成功
     */
    public static boolean addWeiXinFriendScript(String weixinaccount, String screenname) {
        if (findAddImg10805(screenname)) {
            if (clickAdd10805(screenname)) {
                if (clickAddFriend10805(screenname)) {
                    if (clickGreenMagnifier10805(screenname)) {
                        if (findSearchButtn10805(weixinaccount, screenname)) {
                            if (clickSearch10805(screenname)) {
                               if(needValidation10805(screenname)) {
                                   SystemClock.sleep(3000);
                                   exit();
                                   return true;
                               }return false;
                            }return false;
                        }return false;
                    }return false;
                }return false;
            }return false;
        }return false;
    }


    //==============================================1920*1080==5.0寸================================
    //找到微信的+号图标
    private static boolean findAddImg10805(String screenname) {
        SystemClock.sleep(3000);//等待微信APP启动
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(1000);
        //获取加号矩形坐标
        int a = new RootShellCmd().getColors(956, 132, screenname);
        int b = new RootShellCmd().getColors(977, 108, screenname);
        int c = new RootShellCmd().getColors(1002, 132, screenname);
        int d = new RootShellCmd().getColors(971, 125, screenname);//黑色 r=57  g=58   b=63
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到加号");
            return true;
        } else {
            Log.d(TAG, "没找到加号");
            return false;

        }
    }


    //点击微信右上角的加号按钮
    public static boolean clickAdd10805(String screenname) {
        new RootShellCmd().simulateClick(978, 132);//找到+号，点击
        Log.d(TAG, "点击了加号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(542, 417, screenname);
        int b = new RootShellCmd().getColors(564, 417, screenname);
        int c = new RootShellCmd().getColors(553, 406, screenname);
        int d = new RootShellCmd().getColors(547, 411, screenname); //r=57  g=58   b=63
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到了添加朋友按钮");
            return true;
        } else {
            Log.d(TAG, "没找到添加朋友按钮");
            return false;
        }
    }

    //点击加号弹出的List的“添加朋友”按钮
    public static boolean clickAddFriend10805(String screenname) {
        new RootShellCmd().simulateClick(700, 420);//
        Log.d(TAG, "点击了添加朋友按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);  //点了添加朋友截图（绿色放大镜界面）
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(70, 325, screenname);    //63 184 56 绿
        int b = new RootShellCmd().getColors(122, 371, screenname);   //绿
        int c = new RootShellCmd().getColors(106, 355, screenname);   //绿
        int d = new RootShellCmd().getColors(87, 340, screenname);    //白
        if (a == -12601288 && b == -12601288 && c == -12601288 && d == -1) {
            Log.d(TAG, "找到了绿色放大镜");
            return true;
        } else {
            Log.d(TAG, "没找到绿色放大镜");
            return false;
        }
    }


    //点击绿色放大镜区域的EditeText
    public static boolean clickGreenMagnifier10805(String screenname) {
        new RootShellCmd().simulateClick(356, 356);             // 添加朋友界面，点击放大镜
        Log.d(TAG, "点击了绿色放大镜");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //白色放大镜
        int a = new RootShellCmd().getColors(204, 110, screenname);   //白
        int b = new RootShellCmd().getColors(245, 153, screenname); //白
        int c = new RootShellCmd().getColors(232, 139, screenname);  //白
        int d = new RootShellCmd().getColors(216, 123, screenname);  //黑
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到了输入微信账号EditText");
            return true;

        } else {
            Log.d(TAG, "没找到EditText");
            return false;
        }
    }


    //找到输入微信账号后出现的搜索按钮
    public static boolean findSearchButtn10805(String weixinaccount, String screenname) {
        new RootShellCmd().setText(weixinaccount);
        Log.d(TAG, "输入了微信账号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //搜索微信账号，绿框包白色放大镜
        int a = new RootShellCmd().getColors(84, 277, screenname);   //白
        int b = new RootShellCmd().getColors(137, 325, screenname);  //白
        int c = new RootShellCmd().getColors(122, 310, screenname);   //白
        int d = new RootShellCmd().getColors(102, 294, screenname);   //深绿43 162 69

        if (a == -1 && b == -1 && c == -1 && d == -13917627) {
            Log.d(TAG, "找到搜索微按钮");
            return true;
        } else {
            Log.d(TAG, "没找到搜索微按钮");
            return false;
        }
    }

    //输入账号后点击搜索联系人按钮
    public static boolean clickSearch10805(String screenname) {
        new RootShellCmd().simulateClick(450, 300);//搜索微信账号按钮
        Log.d(TAG, "点击了搜索微信按钮");
        SystemClock.sleep(5000);//等待搜索
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //  详细页面有个性签名和地区
        int a = new RootShellCmd().getColors(115, 1218, screenname);     //绿
        int b = new RootShellCmd().getColors(988, 1218, screenname);    //绿
        //详细页面没有个性签名有地区
        int c = new RootShellCmd().getColors(115, 1060, screenname);   //绿
        int d = new RootShellCmd().getColors(988, 1060, screenname);    //绿
        //详细页面没有个签名和地区
        int e = new RootShellCmd().getColors(115, 861, screenname);    //绿
        int f = new RootShellCmd().getColors(988, 861, screenname);    //绿

        if (a == -15028967 && b == -15028967 || c == -15028967 && d == -15028967 || e == -15028967 && f == -15028967) {
            Log.d(TAG, "找到了添加好友的按钮");
            if (a == -15028967 && b == -15028967) {
                new RootShellCmd().simulateClick(500, 1218);//点击添加到通讯录按钮
                Log.d(TAG, "点击了添加好友的按钮，详细页面有个性签名和地区");
            } else if (c == -15028967 && d == -15028967) {
                new RootShellCmd().simulateClick(500, 1060);//点击添加到通讯录按钮
                Log.d(TAG, "点击了添加好友的按钮，详细页面没有个性签名有地区");
            } else new RootShellCmd().simulateClick(500, 861);//点击添加到通讯录按钮
                Log.d(TAG, "点击了添加好友的按钮，详细页面没有个签名和地区");
          return true;
        }
        Log.d(TAG, "没找到添加好友的按钮，原因：用户不存在、好友已添加或者搜索超时");
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        return false;
    }

    //判断是否需要验证申请
    public static boolean needValidation10805(String screenname) {
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //右上角“发送”按钮
        int a = new RootShellCmd().getColors(894,104, screenname);    //绿
        int b = new RootShellCmd().getColors(1040,100, screenname);    //绿
        int c = new RootShellCmd().getColors(889,160, screenname);    //绿
        int d = new RootShellCmd().getColors(1041,162, screenname);    //绿
        if (a == -15028967 && b == -15028967 && c == -15028967 && d == -15028967) {
            Log.d(TAG, "需要验证");
            new RootShellCmd().simulateClick(889,160);//点击发送按钮
            Log.d(TAG, "点击了发送按钮，添加好友任务完成");
            return true;
        }
        Log.d(TAG, "不需要验证，添加好友任务完成");
        return false;

    }

    //退出操作
    public static void exit() {
        new RootShellCmd().simulateKey(4);
        Log.d(TAG, "正在退出");
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
    }
}

   /* //右上角的三点图片
    int a = new RootShellCmd().getColors(1008, 109, screenname);    //白
    int b = new RootShellCmd().getColors(1008, 132, screenname);    //白
    int c = new RootShellCmd().getColors(1007, 154, screenname);    //白
    int d = new RootShellCmd().getColors(1008, 144, screenname);    //黑*/

  /*  //==============================================1280*720==5.0寸=================================
    //找到微信的+号图标
    private static boolean findAddImg(String screenname) {
        SystemClock.sleep(3000);//等待微信APP启动
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(1000);
        int a = new RootShellCmd().getColors(638, 98, screenname);   //判断加号位置 638 98 , 651 83 ,666 98 , 656 93 黑色 r=57  g=58   b=63
        int b = new RootShellCmd().getColors(651, 83, screenname);
        int c = new RootShellCmd().getColors(666, 98, screenname);
        int d = new RootShellCmd().getColors(656, 93, screenname);
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到加号");
            return true;                                    //     1280*720 点击+号坐标   r=57  g=58   b=63
        } else {
            Log.d(TAG, "没找到加号");
            return false;

        }
    }


    //点击微信右上角的加号按钮
    public static boolean clickAdd(String screenname) {
        new RootShellCmd().simulateClick(715, 100);//找到+号，点击
        Log.d(TAG, "点击了加号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(318, 164, screenname);
        int b = new RootShellCmd().getColors(666, 600, screenname);
        int c = new RootShellCmd().getColors(622, 190, screenname); //r=57  g=58   b=63
        int d = new RootShellCmd().getColors(347, 300, screenname);
        if (a == -13026753 && b == -13026753 && c == -13026753 && d == -1) {
            Log.d(TAG, "找到了添加朋友按钮");
            return true;
        } else {
            Log.d(TAG, "没找到添加朋友按钮");
            return false;
        }
    }

    //点击加号弹出的List的“添加朋友”按钮
    public static boolean clickAddFriend(String screenname) {
        new RootShellCmd().simulateClick(600, 320);//666 310     1280*720 添加朋友坐标  r=57  g=58   b=63
        Log.d(TAG, "点击了添加朋友按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);  //点了添加朋友截图（绿色放大镜界面）
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(44, 235, screenname);   //63 184 56 绿
        int b = new RootShellCmd().getColors(74, 236, screenname);   //绿
        int c = new RootShellCmd().getColors(80, 256, screenname);   //绿
        if (a == -12601288 && b == -12601288 && c == -12601288) {
            Log.d(TAG, "找到了绿色放大镜");
            return true;
        } else {
            Log.d(TAG, "没找到绿色放大镜");
            return false;
        }
    }

    //点击绿色放大镜区域的EditeText
    public static boolean clickLvSeFangDaJing(String screenname) {
        new RootShellCmd().simulateClick(240, 240);             // 添加朋友界面，点击放大镜
        Log.d(TAG, "点击了绿色放大镜");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //白色放大镜
        int a = new RootShellCmd().getColors(135, 85, screenname);   //白
        int b = new RootShellCmd().getColors(162, 110, screenname);//白
        int c = new RootShellCmd().getColors(144, 92, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了输入微信账号EditText");
            return true;

        } else {
            Log.d(TAG, "没找到EditText");
            return false;
        }
    }

    //找到输入微信账号后出现的搜索按钮
    public static boolean findSearchButtn(String screenname, String wxnum) {
        new RootShellCmd().setText(wxnum);
        Log.d(TAG, "输入了微信账号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //搜索微信账号，绿框包白色放大镜
        int a = new RootShellCmd().getColors(38, 173, screenname);   //深绿  43 162 69
        int b = new RootShellCmd().getColors(109, 242, screenname);//深绿
        int c = new RootShellCmd().getColors(81, 216, screenname);//白
        if (a == -13917627 && b == -13917627 && c == -1) {
            Log.d(TAG, "找到搜索微按钮");
            return true;
        } else {
            Log.d(TAG, "没找到搜索微按钮");
            return false;
        }
    }

    //输入账号后点击搜索联系人按钮
    public static boolean clickSearch(String screenname) {
        new RootShellCmd().simulateClick(340, 213);//搜索微信账号按钮
        Log.d(TAG, "点击了搜索微信按钮");
        SystemClock.sleep(5000);//等待搜索
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(672, 90, screenname);   //黑
        int b = new RootShellCmd().getColors(672, 98, screenname);//白
        int c = new RootShellCmd().getColors(671, 105, screenname);//黑
        if (a == -13026753 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了右上角的三点按钮");
            return true;
        }
        Log.d(TAG, "没找到右上角的三点按钮,用户不存在或者搜索超时");
        return false;
    }

    //点击微信右上角的三个小点按钮
    public static boolean clickThreePoint(String screenname) {
        new RootShellCmd().simulateClick(715, 100);
        Log.d(TAG, "点击了三点按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(340, 204, screenname);   //白
        int b = new RootShellCmd().getColors(368, 176, screenname);//白
        int c = new RootShellCmd().getColors(360, 200, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了修改备注按钮");
            return true;
        }
        return false;
    }

    //点击三个小点弹出的修改备注名按钮
    public static boolean clickRemarkButton(String screenname) {
        new RootShellCmd().simulateClick(600, 150);
        Log.d(TAG, "点击了修改备注按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(131, 86, screenname); //白
        int b = new RootShellCmd().getColors(179, 83, screenname);//白
        int c = new RootShellCmd().getColors(213, 106, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了修改备注界面");
            return true;
        }
        return false;
    }

    //删除之前的名字，进行写入新的备注名
    public static boolean deleteBeforeAndMark(String remark) {
        Log.d(TAG, "正在修改");
        for (int i = 0; i < 30; i++) {
            SystemClock.sleep(250);
            new RootShellCmd().simulateKey(67);
        }
        SystemClock.sleep(1000);
        new RootShellCmd().setText(remark);
        SystemClock.sleep(2000);
        return true;
    }

    //点击微信右上角的完成按钮
    public static boolean clickFinishButton() {
        new RootShellCmd().simulateClick(715, 100);
        Log.d(TAG, "修改备注已保存");
        SystemClock.sleep(2000);
        return true;
    }

    //退出操作
    public static void exit() {
        new RootShellCmd().simulateKey(4);
        Log.d(TAG, "正在退出");
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
    }


    //============================================1920*1080分辨率==5.7寸=============================
    //找到微信的+号图标
    public static boolean findAddImg1080(String screenname) {
        SystemClock.sleep(3000);//等待微信APP启动
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(1000);
        int a = new RootShellCmd().getColors(964, 121, screenname);
        int b = new RootShellCmd().getColors(986, 98, screenname);
        int c = new RootShellCmd().getColors(1006, 121, screenname);
        int d = new RootShellCmd().getColors(975, 108, screenname);
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到加号");
            return true;
        } else {
            Log.d(TAG, "没找到加号");
            return false;

        }
    }

    //点击微信右上角的加号按钮
    public static boolean clickAdd1080(String screenname) {
        new RootShellCmd().simulateClick(1006, 121);//找到+号，点击
        Log.d(TAG, "点击了加号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(587, 382, screenname);
        int b = new RootShellCmd().getColors(597, 372, screenname);
        int c = new RootShellCmd().getColors(607, 382, screenname);
        int d = new RootShellCmd().getColors(589, 374, screenname);
        if (a == -1 && b == -1 && c == -1 && d == -13026753) {
            Log.d(TAG, "找到了添加朋友按钮");
            return true;
        } else {
            Log.d(TAG, "没找到添加朋友按钮");
            return false;
        }
    }

    //点击加号弹出的List的“添加朋友”按钮
    public static boolean clickAddFriend1080(String screenname) {
        new RootShellCmd().simulateClick(607, 382);
        Log.d(TAG, "点击了添加朋友按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);  //点了添加朋友截图（绿色放大镜界面）
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(62, 304, screenname);   //63 184 56 绿
        int b = new RootShellCmd().getColors(112, 340, screenname);   //绿
        int c = new RootShellCmd().getColors(98, 325, screenname);   //绿
        if (a == -12601288 && b == -12601288 && c == -12601288) {
            Log.d(TAG, "找到了绿色放大镜");
            return true;
        } else {
            Log.d(TAG, "没找到绿色放大镜");
            return false;
        }
    }

    //点击绿色放大镜区域的EditeText
    public static boolean clickLvSeFangDaJing1080(String screenname) {
        new RootShellCmd().simulateClick(260, 325);             // 添加朋友界面，点击放大镜
        Log.d(TAG, "点击了绿色放大镜");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //白色放大镜
        int a = new RootShellCmd().getColors(185, 103, screenname);   //白
        int b = new RootShellCmd().getColors(224, 139, screenname);//白
        int c = new RootShellCmd().getColors(199, 115, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了输入微信账号EditText");
            return true;

        } else {
            Log.d(TAG, "没找到EditText");
            return false;
        }
    }

    //找到输入微信账号后出现的搜索按钮
    public static boolean findSearchButtn1080(String screenname, String wxnum) {
        new RootShellCmd().setText(wxnum);
        Log.d(TAG, "输入了微信账号");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        //搜索微信账号，绿框包白色放大镜
        int a = new RootShellCmd().getColors(43, 215, screenname);   //深绿  43 162 69
        int b = new RootShellCmd().getColors(159, 330, screenname);//深绿
        int c = new RootShellCmd().getColors(112, 284, screenname);//白
        if (a == -13917627 && b == -13917627 && c == -1) {
            Log.d(TAG, "找到搜索微按钮");
            return true;
        } else {
            Log.d(TAG, "没找到搜索微按钮");
            return false;
        }
    }

    //输入账号后点击搜索联系人按钮
    public static boolean clickSearch1080(String screenname) {
        new RootShellCmd().simulateClick(350, 300);//搜索微信账号按钮
        Log.d(TAG, "点击了搜索微信按钮");
        SystemClock.sleep(5000);//等待搜索
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(1014, 112, screenname);   //黑
        int b = new RootShellCmd().getColors(1014, 121, screenname);//白
        int c = new RootShellCmd().getColors(1014, 132, screenname);//黑
        if (a == -13026753 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了右上角的三点按钮");
            return true;
        }
        Log.d(TAG, "没找到右上角的三点按钮,用户不存在或者搜索超时");
        return false;
    }

    //点击微信右上角的三个小点按钮
    public static boolean clickThreePoint1080(String screenname) {
        new RootShellCmd().simulateClick(1006, 121);
        Log.d(TAG, "点击了三点按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(595, 229, screenname);   //白
        int b = new RootShellCmd().getColors(556, 268, screenname);//白
        int c = new RootShellCmd().getColors(565, 271, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了修改备注按钮");
            return true;
        }
        return false;
    }

    //点击三个小点弹出的修改备注名按钮
    public static boolean clickRemarkButton1080(String screenname) {
        new RootShellCmd().simulateClick(756, 250);
        Log.d(TAG, "点击了修改备注按钮");
        SystemClock.sleep(2000);
        new RootShellCmd().getScreen(screenname);
        SystemClock.sleep(2000);
        int a = new RootShellCmd().getColors(181, 103, screenname);   //白
        int b = new RootShellCmd().getColors(207, 141, screenname);//白
        int c = new RootShellCmd().getColors(192, 107, screenname);//黑
        if (a == -1 && b == -1 && c == -13026753) {
            Log.d(TAG, "找到了修改备注界面");
            return true;
        }
        return false;
    }

    //删除之前的名字，进行写入新的备注名
    public static boolean deleteBeforeAndMark1080(String remark) {
        Log.d(TAG, "正在修改");
        for (int i = 0; i < 30; i++) {
            SystemClock.sleep(250);
            new RootShellCmd().simulateKey(67);
        }
        SystemClock.sleep(1000);
        new RootShellCmd().setText(remark);
        SystemClock.sleep(2000);
        return true;
    }

    //点击微信右上角的完成按钮
    public static boolean clickFinishButton1080() {
        new RootShellCmd().simulateClick(1006, 121);
        Log.d(TAG, "修改备注已保存");
        SystemClock.sleep(2000);
        return true;
    }
*/





    /*
    //1280*720 5寸
    SystemClock.sleep(2000);
        new RootShellCmd().simulateClick(500, 130);//点击桌面的微信APP
        if (findAddImg(screenname)) {
            if (clickAdd(screenname)) {
                if (clickAddFriend(screenname)) {
                    if (clickLvSeFangDaJing(screenname)) {
                        if (findSearchButtn(screenname, wxnum)) {
                            if (clickSearch(screenname)) { //账号为数字，跳详细资料页面。账号为字母+数字跳搜索页面，要再点一层才到详细资料页面。
                                if (clickThreePoint(screenname)) {
                                    if (clickRemarkButton(screenname)) {
                                        if (deleteBeforeAndMark(remark)) {
                                            clickFinishButton();//这里的逻辑没处理
                                            exit();
                                            return true;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;*/


//1920*1080 5.7寸
// SystemClock.sleep(2000);
//new RootShellCmd().simulateClick(500, 130);//点击桌面的微信APP

        /*if (findAddImg1080(screenname)) {
            if (clickAdd1080(screenname)) {
                if (clickAddFriend1080(screenname)) {
                    if (clickLvSeFangDaJing1080(screenname)) {
                        if (findSearchButtn1080(screenname, wxnum)) {
                            if (clickSearch1080(screenname)) { //账号为数字，跳详细资料页面。账号为字母+数字跳搜索页面，要再点一层才到详细资料页面。
                                if (clickThreePoint1080(screenname)) {
                                    if (clickRemarkButton1080(screenname)) {
                                        if (deleteBeforeAndMark1080(remark)) {
                                            clickFinishButton1080();//这里的逻辑没处理
                                            exit();
                                            return true;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;*/

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