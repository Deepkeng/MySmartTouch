package com.example.administrator.myapplication.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * Created by psq on 2016/9/19
 */
public class FileUtil {
	private final static String DEFAULT_DIR = "/data/data";


	/**
	 * 读取本地普通文件，将其转化为一个字符串数组
	 * @return
	 */
	public static ArrayList<String> getTxt(String filepath){
		Log.d("MainActivity","读取配置文件");
		try{
			String temp = null;
			File f = new File(filepath);
			String adn="";
			//指定读取编码用于读取中文
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
			ArrayList<String> readList = new ArrayList<String>();

			BufferedReader reader=new BufferedReader(read);
			//bufReader = new BufferedReader(new FileReader(filepath));
			while((temp=reader.readLine())!=null &&!"".equals(temp)){
				readList.add(temp);
			}
			read.close();
			return readList;
		}catch (Exception e) {
			// TODO: handle exception
			Log.e("FileUtil","读取文件--->失败！- 原因：文件路径错误或者文件不存在");
			e.printStackTrace();
			return null;
		}
	}




	/**
	 *   获取SD卡
	 */
	public static String getAndroidPath(String SDpath) {
		String path = Environment.getExternalStorageDirectory().getPath() + SDpath;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 判断文件是否存在
	 * @param path
	 * @return
	 */
	public static boolean fileIsExists(String path) {
		boolean isExists = false;
		try {
			File file = new File(path);
			if (file.exists()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isExists;
	}

	/**
	 * 删除文件
	 * @param path
	 * @return
	 */
	public static void deleteFiles(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 获取跟目�?	 */
	public static String getSd() {
		String sdPath="";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdPath= Environment.getDataDirectory().getAbsolutePath()+ "/";
		}
		return sdPath;
	}
    /**
     * 从assets复制文件到指定位
     * @param myContext
     * @param assetName
     * @param saveDir
     * @param saveName
     * @return
     */
	public static String copyFromAsset(Context myContext, String assetName, String saveDir, String saveName) {
		String filePath = saveDir + "/" + saveName;
		if (fileIsExists(filePath)) {
			return filePath;
		} else {
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				File file = null;
				if (!(file = new File(filePath)).exists()) {
					file.createNewFile();
				}
				
				InputStream is = myContext.getResources().getAssets().open(assetName);
				FileOutputStream fos = new FileOutputStream(filePath);
				byte[] buffer = new byte[7168];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return filePath;
		}
	}
	
	public static String copyFromFile(String srcPath, String saveDir, String saveName) {
		String filePath = saveDir + "/" + saveName;
		if (fileIsExists(filePath)) {
			return filePath;
		} else {
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				File file = null;
				if (!(file = new File(filePath)).exists()) {
					file.createNewFile();
				}
				
				InputStream is = new FileInputStream(srcPath);

				FileOutputStream fos = new FileOutputStream(filePath);
				byte[] buffer = new byte[7168];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return filePath;
		}
	}
	
	

	public static String readFromFile(Context context, String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		try {
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			Log.e("ReadStream", "读取异常");
			return "";
		} finally {
			br.close();
		}
	}
	

	 // 流读取文

	public static String readFromAssets(Context context, String name) throws IOException {
		InputStream openRawResource = context.getAssets().open(name);
		BufferedReader br = new BufferedReader(new InputStreamReader(openRawResource));
		try {
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			Log.e("ReadStream", "读取文件流失");
			return "";
		} finally {
			br.close();
		}
	}

	
	public static ArrayList<String> getLuaFile(String SDpath) {
		ArrayList<String> filelist = null;
		File file = new File(SDpath);
		if (!file.exists()) {
			file.mkdirs();
		}
		filelist = new ArrayList<String>();
		File[] listFiles = file.listFiles();
		for (File fileitem : listFiles) {
			if (fileitem.isFile()) {
				String name = fileitem.getName();
				int length = name.length();
				//if (".lua".equals(name.substring(length-4, length))) {
					filelist.add(name);
				//}
			}
		}
		return filelist;
	}
	
}
