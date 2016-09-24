package com.example.administrator.myapplication;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

/**
 * Created by D on 2016/8/25.
 */
public class ADBUtils {

    public static final String MINICAP_PARENT_PATH = "/data/local/tmp";
    public static final String MINICAP_SO_PATH = "/data/local/tmp/minicap.so";
    public static final String MINICAP_EXECUTE_FILE_PATH = "/data/local/tmp/minicap";

    public static final int MINICAP = 1;
    public static final int MINITOUCH = 2;

    //    CMD 命令
    public static final String COMMAND_MINICAP = " ps grep mmscreen ";
    public static final String COMMAND_MINITOUCH = " ps grep mmtouch ";

    /**
     * 根据传入的文件路径，修改文件权限为可读，可写，可执行
     *
     * @param filePath 文件的路径
     */
    public static void amendFilePermission(String filePath) {
        String cmd = " chmod 777 " + filePath;
        executeCMD(cmd);
    }




    /**
     * 执行命令行语句
     *
     * @param command 命令行指令
     */
    public static void executeCMD(String command) {
        Process process = null;
        DataOutputStream os = null;

        try {
            String cmd = command;

            process = Runtime.getRuntime().exec("su");

            os = new DataOutputStream(process.getOutputStream());

            os.writeBytes(cmd + " \n");
            os.writeBytes(" exit \n");
            os.flush();

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据传入的命令行指令,判断 minicap 或者 minitouch 进程是否存在
     * <p>
     *
     * @param command 命令行指令
     * @return result 判断结果
     */
    public static boolean isProcessExist(String command) {
        Process process = null;
        DataOutputStream os = null;
        DataInputStream is = null;

        try {
            String cmd = command;

            process = Runtime.getRuntime().exec("su");

            os = new DataOutputStream(process.getOutputStream());
            is = new DataInputStream(process.getInputStream());

            os.writeBytes(cmd + " \n");
            os.writeBytes(" exit \n");
            os.flush();

//            读取控制台的输出日志
            int len;
            byte data[] = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while ((len = is.read(data, 0, data.length)) != -1) {
                String message = new String(data, 0, len);
                sb.append(message);
                Log.d("TAG", "checkProcess: sb.toString() ; " + sb.toString());
            }

            process.waitFor();

//              判断控制台输出日志是否存在 root 用户,有则分割字符串，取出
            if (sb.toString().contains("root")) {
                Log.d("TAG", "isProcessExist: root " + true);
                return true;
            } else {
                Log.d("TAG", "isProcessExist: root " + false);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 根据命令行指令，获取进程 pid,并杀死该进程
     *
     * @param command command 命令行
     */
    public static void KillProcess(String command) {
        Process process = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        try {
            String cmd = command;

            process = Runtime.getRuntime().exec("su");

            os = new DataOutputStream(process.getOutputStream());
            is = new DataInputStream(process.getInputStream());

//          执行命令行 （查询是否存在 mmscreen 或者 mmtouch 进程）
            os.writeBytes(cmd + " \n");
            os.writeBytes(" exit \n");
            os.flush();

//            读取控制台输出日志，
            int len;
            byte data[] = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while ((len = is.read(data, 0, data.length)) != -1) {
                String message = new String(data, 0, len);
                sb.append(message);
                Log.d("TAG", "killProcess : sb.toString() ; " + sb.toString());
            }

//          判断日志中是否存在 root 用户
            String arr[] = null;
            if (sb.toString().contains("root")) {
                Log.d("TAG", "killProcess: root " + true);

                arr = sb.toString().split("root" + "      ");

                if (arr[1] != null) {
                    String[] arr2 = arr[1].split(" ");
                    executeCMD(" kill " + arr2[0]);
                    Log.d("TAG", "killProcess: arr2[0] " + arr2[0]);
                }

            } else {
                Log.d("TAG", "isProcessExist: root " + false);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断微信 Thinker 文件夹是否存在，存在则删除内部文件在修改其权限为不可读，不可写，不可执行状态
     */
    public static void isThikerExist() {
        String filePath = "/data/data/com.tencent.mm/tinker";
        amendFilePermission(filePath);
        File thinkerFile = new File(filePath);

        if (thinkerFile.exists()) {

//            Log.d("TAG", "isThikerExist: thinker " + true);

            if (thinkerFile.isDirectory()) {
//                Log.d("TAG", "isThikerExist: Thinker file " + true);
                File files[] = thinkerFile.listFiles();
                if (files == null) {
//                    Log.d("TAG", "isThikerExist: is null");
                } else {
                    for (int i = 0; i < files.length; i++) {
                        amendFilePermission(files[i].getPath());
                        if (files[i].isDirectory()) {
                            executeCMD(" rm " + files[i].getPath());        // 删除内部文件
                        } else {
                            executeCMD(" rmdir " + files[i].getPath());     // 删除内部文件夹
                        }
                    }
                }
            }
            executeCMD(" chmod 000 " + filePath);       // 执行修改Thinker文件夹权限为不可读，不可写，不可执行
        } else {
//            Log.d("TAG", "isThikerExist: thinker " + false);
        }
    }


}
