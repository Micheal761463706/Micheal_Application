package com.micheal.micheal_application.Fragment.Message;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.micheal.micheal_application.Adapter.BaseFragment;
import com.micheal.micheal_application.Entity.Constants;
import com.micheal.micheal_application.Fragment.Message.ui.SelectCity;
import com.micheal.micheal_application.R;

import okhttp3.OkHttpClient;

/**
 *  资讯
 */
public class Fragment_Message extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "print";

    private Button citySelect; // 选择城市按钮

    private ViewPager mViewPager; //头部的图片
    private ListView mListView; // 展示新闻的图文标题
    private OkHttpClient mClient = new OkHttpClient(); // 第三方插件 创建okHttpClient对象


    /**
     *  获取布局id
     */
    @Override
    protected int contextResid() {
        return R.layout.fragment_message;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView(View view) {
        citySelect = (Button) view.findViewById(R.id.citySelectBtn_message);
        citySelect.setOnClickListener(this);
//        mViewPager = (ViewPager) view.findViewById(R.id.viewPage_Message);

    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {

    }


    /**
     * Button的事件监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.citySelectBtn_message:
                // 用有返回值得方式启动Activity
                startActivityForResult(new Intent(getContext(), SelectCity.class), Constants.Code.SELECT_CITY_REQUEST);
                break;
            default:
                break;
        }
    }

    /**
     *  选择城市以后，返回选择城市的信息
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
