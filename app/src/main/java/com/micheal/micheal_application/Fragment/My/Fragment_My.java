package com.micheal.micheal_application.Fragment.My;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.micheal.micheal_application.Adapter.BaseFragment;
import com.micheal.micheal_application.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  我的页面
 */
public class Fragment_My extends BaseFragment{

    private ListView mListView;

    /**
     *  获取布局id
     */
    @Override
    protected int contextResid() {
        return R.layout.fragment_my;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.listViewEnd_my);
    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        List list = new ArrayList<>();
        for (int i = 1; i < 11; i++){
            list.add("功能"+i);
        }

        ArrayAdapter<String> mListViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        mListView.setAdapter(mListViewAdapter);
    }
}
