package com.micheal.micheal_application.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  Fragment基类
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(contextResid(), container,false);
    }

    /**
     * 该方法不属于Fragment的生命周期方法
     * 该方法会紧跟着 onCreateView 方法被调用
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    // 调用该方法返回Fragment的布局
    protected abstract int contextResid() ;

    // 初始化控件方法
    protected void initView(View view){}

    // 加载数据方法
    protected void initData(){}

    // 带动画效果启动的Activity
    public void startActivity(Intent intent , int AnimIn, int AnimOut){
        super.startActivity(intent);
        getActivity().overridePendingTransition(AnimIn, AnimOut);
    }

}
