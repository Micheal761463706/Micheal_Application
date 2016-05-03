package com.micheal.micheal_application.Fragment.Home.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.Fragment.Home.utils.MediaServer;
import com.micheal.micheal_application.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MediaActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar mSeekBar; // 进度条
    private TextView mTimeTV; // 进度条的时间

    private LocalBroadcastManager localBroadcastManager; // 广播接收者
    private MyReceiver myReceiver; // 播放器的自定义接收器

    /**
     *  获取布局id
     */
    @Override
    protected int contentView() {
        return R.layout.activity_media;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar_Media);
        mSeekBar.setOnSeekBarChangeListener(this);

        mTimeTV = (TextView) findViewById(R.id.textView_Media);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.action.intent.timer");
        localBroadcastManager.registerReceiver(myReceiver, intentFilter);

    }

    public void play(View v){
        startService(new Intent(this, MediaServer.class));

    }
    public void stop(View v){
        stopService(new Intent(this, MediaServer.class));
    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 广播接收器
     */
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.action.intent.timer")){
                int max = intent.getIntExtra("maxLen", 0);
                int cur = intent.getIntExtra("curLen", 0);

                mSeekBar.setMax(max);
                mSeekBar.setProgress(cur);
                setTiemrString(max, cur);
            }
        }
    }
    /**
     * 设置时间字符串
     * @param max
     * @param cur
     */
    private void setTiemrString(int max, int cur){
        Date dataMax = new Date(max);
        Date dataCur = new Date(cur);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String maxStr = simpleDateFormat.format(dataMax);
        String curStr = simpleDateFormat.format(dataCur);
        mTimeTV.setText(curStr + "/" + maxStr);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myReceiver);
    }

    /**
     *  SeekBar的监听方法
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //拖动过程中，一直回调该方法
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //当你拖动前，触发该方法
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //当你拖动停止时，触发该方法
        int pro = mSeekBar.getProgress();// 得到当前SeekBar进度
        Intent intent = new Intent("android.intent.action.progress");
        intent.putExtra("pro",pro);
        localBroadcastManager.sendBroadcast(intent);
    }
}
