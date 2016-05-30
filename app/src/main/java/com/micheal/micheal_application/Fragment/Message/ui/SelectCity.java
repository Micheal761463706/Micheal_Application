package com.micheal.micheal_application.Fragment.Message.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.Fragment.Message.Adapter.CityAdapter;
import com.micheal.micheal_application.Fragment.Message.Adapter.LabelView;
import com.micheal.micheal_application.Fragment.Message.Adapter.SibeView;
import com.micheal.micheal_application.Fragment.Message.Entity.CityEntity;
import com.micheal.micheal_application.R;
import com.micheal.micheal_application.util.Constants;
import com.micheal.micheal_application.util.DownUtil;
import com.micheal.micheal_application.util.JsonUtil;

import java.util.List;

/**
 *  城市选择页
 */
public class SelectCity extends BaseActivity implements DownUtil.OnDownComplete, AdapterView.OnItemClickListener, SibeView.OnSideSelectedListener {

    private ListView lvCity; // 城市选择列表
    private List<CityEntity> citysByJSON; // 用于保存解析后的数据
    private CityAdapter cityAdapter; // 城市选择适配器

    private SibeView mSibeView;    // 字母
    private LabelView mLabelView;  // 标签


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
        lvCity.setOnItemClickListener(this);
        cityAdapter = new CityAdapter(this);
        lvCity.setAdapter(cityAdapter);

        mSibeView = (SibeView) findViewById(R.id.sibeView_select_city);     // 字母
        mLabelView = (LabelView) findViewById(R.id.labelView_select_city);  // 标签
        mSibeView.setOnSideSelectedListener(this);
    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        DownUtil.downJSON(Constants.URL.CITY_SELECT, this);// 下载城市Json数据
    }

    // 城市JSON数据下载完回调的方法
    @Override
    public void onDownSuc(String url, Object obj) {
        if(obj != null){
            // json下载完成
            citysByJSON =  JsonUtil.getCityJson((String) obj);
            //Log.e("print", "城市列表: " + citysByJSON);
            cityAdapter.setDatas(citysByJSON);

        } else {
           Log.e("print", "onDownSuc: "+"空了" );
        }
    }

    /**
     *  ListView数据展示的点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("print", "onItemClick: " + citysByJSON.get(position).getCityNamee());
        String CityName = citysByJSON.get(position).getCityNamee();

        Intent intent = getIntent();
        intent.putExtra(Constants.Code.SELECT_CITY_RESUEST + "" , CityName);
        setResult(2,intent);
        finish();

    }

    /**
     *  侧边空间选择字母的回调方法
     * @param index
     * @param strs
     */
    @Override
    public void onSelected(int index, String strs) {
        mLabelView.setText(strs); // 设置自定义控件需要弹出所选择的字母
        int letterLabel = cityAdapter.getIndexByLabel(strs);
        if(letterLabel != -1){
            lvCity.smoothScrollToPositionFromTop(letterLabel,0);
        }
    }
}
