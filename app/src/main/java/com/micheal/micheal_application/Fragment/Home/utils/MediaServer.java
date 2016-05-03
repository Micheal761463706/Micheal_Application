package com.micheal.micheal_application.Fragment.Home.utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.micheal.micheal_application.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  播放器服务管理器
 */
public class MediaServer extends Service {

    private LocalBroadcastManager localBroadcastManager;
    private ServiceReceiver serviceReceiver;

    private MediaPlayer mMediaPlayer;
    private Timer mTimer;

    private int maxLen;
    private int curLen;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         *  通过静态方法创建的MediaPlayer对象，会直接进入就绪状态
         */
        mMediaPlayer = MediaPlayer.create(this, R.raw.hmm);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        serviceReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.progress");
        localBroadcastManager.registerReceiver(serviceReceiver, intentFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
        }else {
            mMediaPlayer.start();
            timer();
        }
        return super.onStartCommand(intent,flags,startId);
    }

    /**
     * 定时器方法 -- 用于实时给activity传输播放进度
     */
    private void timer(){
        if (mTimer == null){
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {

                    try {
                        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
                            // 得到歌曲的总长度和当前播放进度
                            maxLen = mMediaPlayer.getDuration(); // 得到歌曲总时长
                            curLen = mMediaPlayer.getCurrentPosition(); // 得到歌曲当前播放进度

                            Intent intent = new Intent("android.action.intent.timer");
                            intent.putExtra("maxLen", maxLen);
                            intent.putExtra("curLen", curLen);
                            localBroadcastManager.sendBroadcast(intent);
                        }else {
                            mTimer.cancel();
                            mTimer = null;
                   }
                } catch (Exception e){
                     e.printStackTrace();
                        mTimer.cancel();
                        mTimer = null;
                    }
                }
            },0,500);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            // 销毁 回收资源
            mMediaPlayer.release();
        }
        if(serviceReceiver != null){
            localBroadcastManager.unregisterReceiver(serviceReceiver);
        }
    }

    /**
     *  进度条拖动管理
     */
    class ServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.intent.action.progress")){
                int pro = intent.getIntExtra("pro", 0);// 得到拖动的进度
                mMediaPlayer.seekTo(pro);
            }
        }
    }

}
