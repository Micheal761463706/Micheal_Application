package com.micheal.micheal_application.Fragment.Instrument;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.micheal.micheal_application.Fragment.Instrument.ui.Baidu_Activity;
import com.micheal.micheal_application.Adapter.BaseFragment;
import com.micheal.micheal_application.R;

/**
 *  工具
 */
public class Fragment_instrument extends BaseFragment implements View.OnClickListener {

    private Button baiduBtn;

    /**
     * 获取布局id
     */
    @Override
    protected int contextResid() {
        return R.layout.fragment_instrument;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView(View view) {
        baiduBtn = (Button) view.findViewById(R.id.baiduBtn_Instrment);
        baiduBtn.setOnClickListener(this);
    }

    /**
     *  Button 监听事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.baiduBtn_Instrment:// 百度地图按钮
                startActivity(new Intent(getActivity(), Baidu_Activity.class), R.anim.activity_in_left, R.anim.activity_in_up);
                break;
        }

    }

}
