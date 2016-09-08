package com.example.administrator.myapplication;

import java.io.OutputStream;

/**
 * Created by psq on 2016/9/7
 */
public class RootShellCmd {
    private OutputStream os = null;

    public  void exec(String cmd) {
        try {
            if (os == null) {
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write(cmd.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void simulateClick(int x, int y) {
        exec("input tap " + Integer.toString(x) + " " + Integer.toString(y) + "\n");
    }

    public  void simulateSwipeUpDown(int y, int y1) {
        exec("input swipe 300 " + Integer.toString(y) + " 300 " + Integer.toString(y1) + " 100 " + "\n");
    }

    public  void simulateSwipeLeftRight(int x, int x1) {
        exec("input swipe " + Integer.toString(x) + " 640 " + Integer.toString(x1) + " 640 " + "100 " + "\n");
    }

    public  void simulateKey(int keyCode) {
        exec("input keyevent " + keyCode + "\n");
    }

    public  void setText(String content){
        exec("input text "+content+"\n");
    }
    public  void getScreen(String imageName) {
        exec("screencap /sdcard/backup/" + imageName + ".png"+"\n");
    }
}





