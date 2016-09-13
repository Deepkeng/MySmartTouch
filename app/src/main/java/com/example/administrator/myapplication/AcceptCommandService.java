package com.example.administrator.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static java.lang.Integer.parseInt;

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


        // 创建接收端的Socket对象
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(12345);
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

                int i = parseInt(s);

                Log.d("AcceptCommandService","from " + ip + " data is : " + s);
               // new RootShellCmd().simulateKey(i);
              //  new RootShellCmd().simulateSwipe(s);
            }



        } catch (SocketException e) {
            e.printStackTrace();
        }


        // 释放资源
        // 接收端应该一直开着等待接收数据，是不需要关闭
        // ds.close();
    }
}
