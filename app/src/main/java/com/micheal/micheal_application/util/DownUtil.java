package com.micheal.micheal_application.util;

import android.graphics.Bitmap;
import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  下载工具类
 */
public class DownUtil {

    /**
     *  创建一个线程池，共有5条线程
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static Handler handler = new Handler();

    /**
     *  下载JSON
     */

    public static void downJSON(final String string,final OnDownComplete onDownComplete){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 子线程中执行，被线程池管理
                final String json = HttpUtil.getJSONByURL(string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDownComplete.onDownSuc(string, json);
                    }
                });

            }
        });

    }

    /**
     *  下载BitMap
     */
    public static void downBitMap(final String string, final OnDownComplete onDownComplete){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 子线程中执行，被线程池所管理
                final Bitmap bitmap = HttpUtil.getBitmapByURL(string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDownComplete.onDownSuc(string, bitmap);
                    }
                });
            }
        });
    }


    public interface OnDownComplete{
        void onDownSuc(String url, Object obj);
    }

}
