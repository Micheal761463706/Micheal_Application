package com.micheal.micheal_application.Activity.GuideView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.micheal.micheal_application.Activity.MainActivity;
import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.R;

public class WelcomePage extends BaseActivity {

    private Runnable mRunnable;
    private Handler mHandler = new Handler();

    // 获取布局id
    @Override
    protected int contentView() {
        return R.layout.activity_welcome_page;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView() {

        /**
         *  通过SharedPreferences判断是否是第一次启动
         *  方法用意注释：这样操作，除非我们在后台清除数据，或者卸载，否则isFirstRun值一直存在。
         */
        //此处表示该应用程序专用
        SharedPreferences sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        //此处表示如果key "isFirstRun"对应的value没有值则默认为true，否则就把对应的value取出赋值给变量isFirstRun
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun){
            editor.putBoolean("isFirstRun", false);
            editor.commit();//将isFirstRun写入editor中保存
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 第一次运行
                    startActivity(new Intent(getApplicationContext(),IntroPage.class), R.anim.activity_in_up, R.anim.activity_out_down_0_50);
                    finish();
                }
            },3000);
            }else{
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 非第一次运行
                        startActivity(new Intent(getApplicationContext(), MainActivity.class), R.anim.activity_in_right, R.anim.activity_out_down_0_50);
                        finish();
                    }
                },3000);
            }


    }
}
