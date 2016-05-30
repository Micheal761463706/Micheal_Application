package com.micheal.micheal_application.Activity;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.Fragment.Home.Fragment_Home;
import com.micheal.micheal_application.Fragment.Instrument.Fragment_instrument;
import com.micheal.micheal_application.Fragment.Message.Fragment_Message;
import com.micheal.micheal_application.Fragment.My.Fragment_My;
import com.micheal.micheal_application.R;

/**
 *  首页
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private long exitTime = 0 ; // 点击返回退出程序，默认值

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;

    /**
     *  获取布局id
     */
    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }



    /*
     * 初始化方法
     */

    @Override
    protected void initView() {
        // 初始化帧布局
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout_main);
        // 初始化选择按钮组
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_main);
    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        // 设置点击变化监听
        mRadioGroup.setOnCheckedChangeListener(this);
        // 默认点击第一个RadioButton
        mRadioGroup.getChildAt(0).performClick();
    }

    /**
     * RadioGroup点击监听方法
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {
        switch (position){
            case R.id.home_rb_main:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, new Fragment_Home())
                        .commit();
                break;
            case R.id.zixun_rb_main:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, new Fragment_Message())
                        .commit();
                break;
            case R.id.instrument_rb_main:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, new Fragment_instrument())
                        .commit();
                break;
            case R.id.more_rb_main:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, new Fragment_My())
                        .commit();
                break;
        }
    }



    /**
     *  当用户在主页面点击返回时
     *     按第一次：提示是否退出程序
     *     连续点击：则退出应用
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(),"再按一次退出程序！",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


}
