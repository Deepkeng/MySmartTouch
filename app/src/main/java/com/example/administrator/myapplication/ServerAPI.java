package com.example.administrator.myapplication;

import android.util.Log;
import com.example.administrator.myapplication.utils.HttpUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by D on 2016/8/31.
 */
public class ServerAPI {

    private static final String TAG = ServerAPI.class.getSimpleName();

    private final static String HTTP = "http://";
    private final static String HOST = "192.168.1.30/";
    private final static String BASE_URL = HTTP + HOST;


    /**
     * sign in server
     *
     * @param account  account
     * @param password password
     * @return request result
     */
    public static String login(String account, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", account);
            jsonObject.put("password", password);
            jsonObject.put("timespan", (System.currentTimeMillis() / 1000) + "");
            jsonObject.put("device", WeChatDBUtils.obtainIMEICode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HttpUtils.doPost(BASE_URL + "login", jsonObject);
    }


    /**
     * send heart breath
     *
     * @param account login accouont
     * @return request result
     */
    public static String ping(String account) {
        JSONObject object = new JSONObject();
        try {
            object.put("account", account);
            object.put("timespan", (System.currentTimeMillis() / 1000) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpUtils.doPost(BASE_URL + "ping", object);
    }


    /**
     * send current message to server
     *
     * @param token   token
     * @param message message content
     * @return request result
     */
    public static String sendCurrentMsg(String token, String message) {
        JSONObject object = null;
        try {
            object = new JSONObject(message);
            object.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "sendCurrentMsg: json object : " + object.toString());
        return HttpUtils.doPost(BASE_URL + "wxmsg", object);
    }


    /**
     * upload history chat message
     *
     * @param token   token
     * @param message message
     * @return request result
     */
    public static String transferChatMessage(String token, JSONArray message) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("msgs", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "transferChatMessage: " + object.toString());
        return HttpUtils.doPost(BASE_URL + "wxmsgs", object);
    }


    /**
     * upload weChat friends infomation
     *
     * @param token    token
     * @param accouont sign in account
     * @param message  friend infomation
     * @return result
     */
    public static String uploadContacts(String token, String accouont, String message) {
        JSONObject object = new JSONObject();
        try {
            object.put("weixin", accouont);
            object.put("timespan", (System.currentTimeMillis() / 1000) + "");
            object.put("token", token);
            object.put("accounts", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "pushContacts: object str :" + object.toString());
        return HttpUtils.doPost(BASE_URL + "add_friends", object);
    }
}
