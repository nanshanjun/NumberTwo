package com.bwei.dingweimap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor mCurrentMarker;
    private MyLocationListener myLocationListener;
    private LocationClientOption option;
    private LocationClient locationClient;

    private boolean isfirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        initData();

    }

    private void initData() {

        myLocationListener = new MyLocationListener();

        locationClient = new LocationClient(this);

        locationClient.registerLocationListener(myLocationListener);

        option = new LocationClientOption();

        option.setCoorType("bd09ll");

        option.setIsNeedAddress(true);

        option.setEnableSimulateGps(true);

        option.setScanSpan(1000);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (locationClient.isStarted()){

        locationClient.start();

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        locationClient.stop();
    }

    class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();

            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);

            if (isfirst){

                LatLng latLng=new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());

                MapStatusUpdate msu= MapStatusUpdateFactory.newLatLng(latLng);

                mBaiduMap.animateMapStatus(msu);

                isfirst=false;
            }

        }
    }

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
