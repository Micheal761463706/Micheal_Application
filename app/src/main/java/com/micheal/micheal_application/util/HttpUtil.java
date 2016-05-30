package com.micheal.micheal_application.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  下载数据工具类
 */
public class HttpUtil {

    private static byte[] getBytesByURL(String urlstr){
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024*10];
            while((len = inputStream.read(b)) != -1){
                out.write(b, 0 , len);
            }
            inputStream.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("print", "打印了空");
        return null;
    }

    /**
     *  下载JSON数据
     */
    public static String getJSONByURL(String str){
        byte[] b = getBytesByURL(str);
        if(b != null){
            try {
                return new String (b, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  下载图片数据
     */
    public static Bitmap getBitmapByURL(String str){
        byte[] b = getBytesByURL(str);
        if(b != null){
            try {
                return new BitmapFactory().decodeByteArray(b, 0 , b.length);
            } catch ( Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


}
