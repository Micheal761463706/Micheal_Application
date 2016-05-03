package com.micheal.micheal_application.Activity;

import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.R;
/**
 *  百度地图类
 */
public class Baidu_Activity extends BaseActivity implements View.OnClickListener {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;

    private Button ptBtn,wxBtn,cjBtn,jtBtn,dwBtn; // 普通、卫星、场景、交通

    @Override
    protected int contentView() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        return R.layout.activity_baidu;
    }

    @Override
    protected void initView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        ptBtn = (Button) findViewById(R.id.putongMode_Baidu);
        ptBtn.setOnClickListener(this);
        wxBtn = (Button) findViewById(R.id.weixingMode_Baidu);
        wxBtn.setOnClickListener(this);
        cjBtn = (Button) findViewById(R.id.changjingMode_Baidu);
        cjBtn.setOnClickListener(this);
        jtBtn = (Button) findViewById(R.id.jiaotongMode_Baidu);
        jtBtn.setOnClickListener(this);
        dwBtn = (Button) findViewById(R.id.dingweiMode_Baidu);
        dwBtn.setOnClickListener(this);
    }

    /**
     *  Button 事件监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.putongMode_Baidu://普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.weixingMode_Baidu://卫星地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.changjingMode_Baidu:
                // 空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。
                // 使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case R.id.jiaotongMode_Baidu://开启交通图
                mBaiduMap.setTrafficEnabled(true);
                break;
            case R.id.dingweiMode_Baidu:
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
                break;
        }
    }


    /**
     *  百度地图生命周期管理
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


}
