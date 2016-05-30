package com.micheal.micheal_application.Fragment.Message;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.micheal.micheal_application.Adapter.BaseFragment;
import com.micheal.micheal_application.Fragment.Message.ui.SelectCity;
import com.micheal.micheal_application.R;
import com.micheal.micheal_application.util.Constants;

import java.util.ArrayList;
import java.util.List;

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

    private ListView aListView;//模拟数据

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
        aListView = (ListView) view.findViewById(R.id.listView_message);
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 20 ; i++){
            list.add("模拟数据"+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        aListView.setAdapter(adapter);
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
        // 设置当前城市Botton名称
        citySelect.setText(data.getStringExtra(String.valueOf(Constants.Code.SELECT_CITY_RESUEST)));
    }
}
