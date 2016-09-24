package com.example.administrator.myapplication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.administrator.myapplication.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by psq on 2016/9/12
 */
public class AcceptCommandService extends IntentService {
    private final static String HTTP = "http://";
    private final static String HOST = "192.168.1.30/";
    private final static String BASE_URL = HTTP + HOST;
    private final static String CONTACT = BASE_URL + "contacts";


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
    /*    Bundle extras = intent.getExtras();
        String IMEI = extras.getString("手机IMEI");
        Log.d("MainActivity","Activity传过来的："+IMEI);*/

        int errcode;
        SharedPreferences mSharedPreferences;
        //请求数据前，先登录admin admin,使用post请求
        String loginBack = login("admin", "admin");
        Log.d("AcceptCommandService", "loginBack" + loginBack);
        try {
            //登录成功后解析返回的json
            JSONObject loginBackJson = new JSONObject(loginBack);
            errcode = loginBackJson.getInt("errcode");

            //data里又是一个json对象，所以继续解析
            String data = loginBackJson.getString("data");
            JSONObject tokenJson = new JSONObject(data);
            String token = tokenJson.getString("token");

            //把token转成JSONObject,当请求参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);

            //把token存到sp
            mSharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_APPEND);
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString("token", token);
            //edit.commit();
            edit.apply();


            Log.d("AcceptCommandService", " token:" + token);
            if (errcode == 0) {
                //向192.168.1.30/contacts请求联系人数据
                String getContactJson = HttpUtils.doPost(CONTACT, jsonObject);
                Log.d("AcceptCommandService", getContactJson);

                //解析返回的json
                JSONObject json = new JSONObject(getContactJson);
                int errcode1 = json.getInt("errcode");
                String data1 = json.getString("data");

                JSONObject json1 = new JSONObject(data1);
                String account = json1.getString("account");

                if (errcode1 == 0) {
                    runAddFriendScript(account);//获取到联系人立即跑脚本
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String login(String account, String password) {
        String mIMEI = ((TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.d("AcceptCommandService", "IMEI:" + mIMEI);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", account);
            jsonObject.put("password", password);
            jsonObject.put("timespan", (System.currentTimeMillis() / 1000) + "");
            jsonObject.put("device", mIMEI);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HttpUtils.doPost(BASE_URL + "login", jsonObject);
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

    /**
     * 执行脚本
     *
     * @param weixinnum 要添加的微信账号
     */
    private void runAddFriendScript(String weixinnum) {
        //只能ASCII码，要输入汉字https://github.com/senzhk/ADBKeyBoard
        boolean isFinish = ScriptSet.addWeiXinFriendScript(weixinnum, "9517");
        if (isFinish) {
            Log.d("ScriptSet", "执行成功");
        }
        if (!isFinish) {
            Log.d("ScriptSet", "修改失败，执行第2遍");
            boolean a = reRun(weixinnum);
            if (!a) {
                Log.d("ScriptSet", "修改失败，执行第3遍");
                boolean b = reRun(weixinnum);
                if (!b) {
                    Log.d("ScriptSet", "执行失败，执行结束");
                    /*Log.d("ScriptSet", "修改失败，执行第4遍");
                    boolean c = reRun(weixinnum);
                    if (c) {
                        Log.d("ScriptSet", "执行成功");
                    } else {
                        Log.d("ScriptSet", "执行失败，执行结束");
                    }*/
                }
            }
        }
    }

    //重新执行脚本
    private boolean reRun(String weixinnum) {
        return ScriptSet.addWeiXinFriendScript(weixinnum, "9517");
    }

}
