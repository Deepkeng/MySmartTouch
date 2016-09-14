package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText mEditText1;
    private EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //启动服务
        startService(new Intent(getApplicationContext(), AcceptCommandService.class));
       /* mEditText1 = (EditText) findViewById(R.id.et_1);
        mEditText2 = (EditText) findViewById(R.id.et_2);*/
        /*try {
            Process process = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       /* String weixinnum = mEditText1.getText().toString().trim();
        String remarkname = mEditText2.getText().toString().trim();*/
        // if (!weixinnum.isEmpty() && !remarkname.isEmpty()) {
       // runScript("13632316531", "119");
        //  } else {
        //      Toast.makeText(this, "账号或备注名为空", Toast.LENGTH_SHORT).show();
        // }

    }




    public void startMission(View view) {
        finish();
    }


}

   /*
   execShellCmd("getevent -p");
        execShellCmd("sendevent /dev/input/event0 1 158 1");
        execShellCmd("sendevent /dev/input/event0 1 158 0");
        execShellCmd("input keyevent 3");//home
        execShellCmd("input text  'helloworld!' ");
        execShellCmd("input tap 168 252");
        execShellCmd("input swipe 100 250 200 280");

   private void execShellCmd(String cmd) {

        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }*/
