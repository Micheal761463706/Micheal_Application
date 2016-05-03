package com.micheal.micheal_application.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView());
        initView();// 初始化方法
        initData();// 加载数据
    }

    /*
     *  该方法由子类实现，用于设置子类的布局文件
     */
    protected abstract int contentView();

    /**
     *  初始化方法
     */
    protected void initView(){}

    /**
     *  加载数据方法
     */
    protected void initData (){}

    /**
     *  带过场动画的Activity
     */
    public void startActivity(Intent intent, int animIn, int animOut){
        super.startActivity(intent);
        overridePendingTransition(animIn, animOut);
    }


}
