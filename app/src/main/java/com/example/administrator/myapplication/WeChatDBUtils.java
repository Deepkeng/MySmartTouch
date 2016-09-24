package com.example.administrator.myapplication;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.myapplication.utils.MD5Utils;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by D on 2016/9/5.
 */
public class WeChatDBUtils {

    private static final String TAG = WeChatDBUtils.class.getSimpleName();
    private static String pathString = "";
    public static String root = "/sdcard/";
    public static String copyDBName = "copyDB.db";
    public static String copyDBPath = root + copyDBName;
    private static String configName = "config.txt";
    private static final String CONFIG_PATH = root + configName;
    private static final String WECHAT_DB_NAME = "EnMicroMsg.db";
    private static final String WECHAT_DB_PARENT_DIRECTORY = "/data/data/com.tencent.mm/MicroMsg";
    private static final int INTERVAL = 1000 * 60 * 10;     //copy wechat database interval

    /**
     * save application config
     *
     * @param config config file
     */
    private static void saveConfig(String config) {
        File configFile = new File(CONFIG_PATH);
        FileOutputStream fos = null;
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            fos = new FileOutputStream(configFile);
            fos.write(config.getBytes(), 0, config.getBytes().length);
            fos.flush();
            Log.d(TAG, "saveConfig: save config  success ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * obtain application config file
     *
     * @return config file message
     */
    private static String getConfig() {
        String jsonStr = "";
        FileInputStream fis = null;
        File config = new File(CONFIG_PATH);
        try {
            if (config.exists()) {
                byte[] data = new byte[1024];
                int len;
                fis = new FileInputStream(config);
                while ((len = fis.read(data, 0, data.length)) != -1) {
                    jsonStr = new String(data, 0, len, "UTF-8");
                    Log.d(TAG, "getConfig: jsonStr " + jsonStr);
                }
                Log.d(TAG, "getConfig:  get config success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonStr;
    }


    /**
     * parse json about config file
     *
     * @param json come from getConfig() return ;
     * @return config object
     */
    private static Config parseConfig(String json) {
        Config config = new Config();
        try {
            JSONObject object = new JSONObject(json);
            config.setAmendTime(object.getLong("amendTime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return config;
    }


    /**
     * judge duplicate database exists ?
     *
     * @return result
     */
    private static boolean isCopyDBExists() {
        File copyDB = new File(copyDBPath);
        if (copyDB.exists()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * judge the file be amend
     * <p/>
     * if the database amend time greater than config's amend time about 10 min , reset the config's amend time and copy the database
     */
    private static boolean isDBAmend() {
        boolean flag = true;

        Config config = parseConfig(getConfig());

        File wxDB = new File(pathString);

        if (config != null && wxDB.exists()) {
            long time = wxDB.lastModified();
            if ((time - config.getAmendTime()) > INTERVAL) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }


    /**
     * calculate password to open database
     *
     * @return the Database password
     */
    public static String calculatePsw() {
        String password = "";

        String imei = obtainIMEICode();
//            get uin code
        String uinCode = obtainUinCode();

        String encryptionStr = "";

        if (!TextUtils.isEmpty(imei) && !TextUtils.isEmpty(uinCode)) {
            try {
                encryptionStr = MD5Utils.get32MD5Value(imei + uinCode);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

//            caculate password
        if (!TextUtils.isEmpty(encryptionStr)) {
            password = encryptionStr.substring(0, 7);
        }
        Log.d(TAG, "calculatePassword: password : " + password);
        return password;
    }


    /**
     * obtain phone imei code
     *
     * @return result
     */
    public static String obtainIMEICode() {
        String imei;
        imei = ((TelephonyManager) ApplicationImpl.getContext().getSystemService(ApplicationImpl.getContext().TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }


    /**
     * parse xml file ,obtain weChat uin code
     *
     * @return weChat uin code
     */
    public static String obtainUinCode() {
        String value = null;
        InputStream inputStream = null;
        try {
            String uinFile = "/data/data/com.tencent.mm/shared_prefs/system_config_prefs.xml";

            ADBUtils.amendFilePermission(uinFile);
//            amendFilePermission(uinFile);
            File file = new File(uinFile);

            inputStream = new FileInputStream(file);
            //获取工厂对象，以及通过DOM工厂对象获取DOMBuilder对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析XML输入流，得到Document对象，表示一个XML文档
            Document document = builder.parse(inputStream);
            //获得文档中的次以及节点
            Element element = document.getDocumentElement();
            NodeList personNodes = element.getElementsByTagName("int");
            for (int i = 0; i < personNodes.getLength(); i++) {
                Element personElement = (Element) personNodes.item(i);
                value = personElement.getAttribute("value");
//                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    /**
     * obtain copy from weChat EnMicroMsg.db
     *
     * @return the copy database
     */
    public static File obtainDatabaseFile() {
        File copyDB = null;
//        String databasePath = getMicroMsgPath();
        String databasePath = getCurrentDBPath();

        if (!TextUtils.isEmpty(databasePath)) {
//           amend file permission
            if (isDBAmend()) {
                ADBUtils.amendFilePermission(databasePath);
                copyDB = copyFileToSDCard(ApplicationImpl.getContext().getDatabasePath(databasePath));
            } else {
                copyDB = new File(copyDBPath);
            }
        }

        return copyDB;
    }


    /**
     * find EnMicroMsg.db file
     *
     * @return weChat EnMicroMsg.db file path
     */
    @Deprecated
    public static String getMicroMsgPath() {

        ADBUtils.amendFilePermission(WECHAT_DB_PARENT_DIRECTORY);

        Config config = parseConfig(getConfig());
        if (config != null && !config.getFilePath().equals("")) {
            pathString = config.getFilePath();
        } else {
            searchFiles(WECHAT_DB_NAME, new File(WECHAT_DB_PARENT_DIRECTORY));
        }
        String path = pathString;
        return path;
    }


    /**
     * obtain wechat database's path about current sign in account
     *
     * @return wechant database path
     */
    public static String getCurrentDBPath() {
        String dbPath = "";
        try {
            ADBUtils.amendFilePermission(WECHAT_DB_PARENT_DIRECTORY);

            String uinCode = WeChatDBUtils.obtainUinCode();

            String encryptionPath = MD5Utils.get32MD5Value("mm" + uinCode);       //wechat encrypt method :md5(mm + wechat uin code )

            ADBUtils.amendFilePermission(WECHAT_DB_PARENT_DIRECTORY + File.separator + encryptionPath);

            dbPath = WECHAT_DB_PARENT_DIRECTORY + File.separator + encryptionPath + File.separator + WECHAT_DB_NAME;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return dbPath;
    }


    /**
     * search files by keyword
     *
     * @param keyword  database's name
     * @param filepath database parent path
     */
    @Deprecated
    private static void searchFiles(String keyword, File filepath) {
        if (filepath.isDirectory()) {
            try {
                File[] mFile = filepath.listFiles();

                for (int i = 0; i < mFile.length; i++) {
                    if (filepath.canRead())
                        ADBUtils.amendFilePermission(mFile[i].getPath());
                    searchFiles(keyword, mFile[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (filepath.getName().equals(keyword)) {

                pathString = filepath.getPath();

//                save configuration
//                Config config = parseConfig(getConfig());
//                if (config == null) {
//                    config = new Config();
//                }
//                JSONObject json = new JSONObject();
//                try {
//                    json.put("filePath", pathString);
//                    json.put("amendTime", config.getAmendTime());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                saveConfig(json.toString());

                Log.d(TAG, "searchFiles: file path : " + pathString);
            }
        }
    }


    /**
     * copy file to sdcard's root directory
     *
     * @param file weChat source database
     * @return copy database
     */
    public static File copyFileToSDCard(File file) {

        ADBUtils.amendFilePermission(copyDBPath);
        File copyFile = new File(copyDBPath);
        try {
            copyFile.createNewFile();
            copyFile.setReadable(true);
            copyFile.setWritable(true);
            copyFile.setExecutable(true);

            FileOutputStream outputStream = new FileOutputStream(copyFile);
            FileInputStream inputStream = new FileInputStream(file);

            int len;
            byte b[] = new byte[1024];

            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }

            outputStream.flush();

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        save configuration
        JSONObject object = new JSONObject();
        {
            try {
                object.put("amendTime", file.lastModified());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        saveConfig(object.toString());

        return copyFile.exists() ? copyFile : null;
    }
}
