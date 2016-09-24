package com.example.administrator.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by psq on 2016/9/12
 */
public class AcceptCommandService extends IntentService {
    public AcceptCommandService() {
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AcceptCommandService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {




    }

      /*   // 创建接收端的Socket对象
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(12345);//端口号：12345
            while (true) {
                // 创建一个包裹
                byte[] bys = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bys, bys.length);

                // 接收数据
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 解析数据
                String ip = dp.getAddress().getHostAddress();
                String s = new String(dp.getData(), 0, dp.getLength());
                // int i = parseInt(s);
                Log.d("AcceptCommandService", "from " + ip + " data is : " + s);
                // new RootShellCmd().simulateKey(i);
                //new RootShellCmd().simulateSwipe(s);
                //JSONObject json = new JSONObject();



;
            }


        } catch (SocketException e) {
            e.printStackTrace();
        }


        // 释放资源
        // 接收端应该一直开着等待接收数据，是不需要关闭
        // ds.close();*/



    /**
     * 执行脚本
     *
     * @param weixinnum  要添加的微信账号
     * @param remarkname 要改成的备注名
     */
    private void runScript(String weixinnum, String remarkname) {
        boolean isFinish = ScriptSet.addWeiXinFriendScript(weixinnum, "9517");//备注名只能ASCII码，要输入汉字https://github.com/senzhk/ADBKeyBoard
        if (isFinish) {
            Log.d("ScriptSet", "执行成功");
        }
        if (!isFinish) {
            Log.d("ScriptSet", "修改失败，执行第2遍");
            boolean a = reRun(weixinnum, remarkname);
            if (!a) {
                Log.d("ScriptSet", "修改失败，执行第3遍");
                boolean b = reRun(weixinnum, remarkname);
                if (!b) {
                    Log.d("ScriptSet", "修改失败，执行第4遍");
                    boolean c = reRun(weixinnum, remarkname);
                    if (c) {
                        Log.d("ScriptSet", "执行成功");
                    } else {
                        Log.d("ScriptSet", "执行失败，执行结束");
                        back();
                    }
                }
            }
        }
    }

    //重新执行脚本
    private boolean reRun(String weixinnum, String remarkname) {
        back();
        return ScriptSet.addWeiXinFriendScript(weixinnum, "9517");
    }

    //回退
    private void back() {
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        SystemClock.sleep(1000);
        new RootShellCmd().simulateKey(4);
        new RootShellCmd().simulateKey(4);
    }

}
