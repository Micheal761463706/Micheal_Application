package com.micheal.micheal_application.Fragment.Message.ui;

import android.util.Log;
import android.widget.ListView;

import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.Entity.Constants;
import com.micheal.micheal_application.Fragment.Message.utils.OkHttp;
import com.micheal.micheal_application.R;

/**
 *  城市选择页
 */
public class SelectCity extends BaseActivity {

    private ListView lvCity; // 城市选择列表


    /**
     *  获取布局id
     */
    @Override
    protected int contentView() {
        return R.layout.activity_select_city;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView() {
        lvCity = (ListView) findViewById(R.id.citys_ListView_SelectCity);

    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        String url = new OkHttp().Get(Constants.URL.CITY_SELECT);
        if(url != null)
        {
            Log.d("print", "iii: " + url);
        }
    }


}
