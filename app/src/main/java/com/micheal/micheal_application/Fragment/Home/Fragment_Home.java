package com.micheal.micheal_application.Fragment.Home;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.micheal.micheal_application.Adapter.BaseFragment;
import com.micheal.micheal_application.Fragment.Home.ui.MediaActivity;
import com.micheal.micheal_application.R;

import java.util.ArrayList;

/**
 *  主页
 */
public class Fragment_Home extends BaseFragment implements AdapterView.OnItemClickListener {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> menuLists;
    private TextView tv;

    /**
     *  获取布局id
     */
    @Override
    protected int contextResid() {
        return R.layout.fragment_home;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView(View view) {
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout_home);
        mDrawerList = (ListView) view.findViewById(R.id.listView_home);
        tv = (TextView) view.findViewById(R.id.textView_home);

    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        // 模拟展示的标签
        menuLists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menuLists.add("菜单"+i);
        }

        // 选择适配器
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuLists);
        //设置适配器
        mDrawerList.setAdapter(adapter);
        //设置点击监听器
        mDrawerList.setOnItemClickListener(this);

        /**
        *  测试文本
         */
        ViewTreeObserver observer = tv.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if(tv.getLineCount() > 2){
                    int lineEndIndex = tv.getLayout().getLineEnd(1);
                    String text = tv.getText().subSequence(0,lineEndIndex-6)+"......";
                    tv.setText(text);
                }
            }
        });



    }

    /**
     *  选择菜单的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("--------> ", "onItemClick: "+menuLists.get(position));
        switch (position){
            case 0:
                Intent intent = new Intent(getActivity(), MediaActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }

        // 点击后隐藏list
        mDrawerLayout.closeDrawer(mDrawerList);
    }



}
