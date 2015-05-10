package com.aiparent.parentsapp.servicer;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.aiparent.parentsapp.MyApplication;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by weilanzhuan on 2015/5/2.
 */
public class GpsService extends Service implements AMapLocationListener {
    private LocationManagerProxy aMapManager;

    private  void initMapManager(){
        aMapManager=LocationManagerProxy.getInstance(GpsService.this);
        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        aMapManager.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 5, this);
        aMapManager.setGpsEnable(false);
    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        // 通过 AMapLocation.getAMapLocException()方法获取定位异常对象，再通过异常对象返回的错误码判断是或否定位成功。
        if(aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0){
            //获取位置信息
            Double geoLat = aMapLocation.getLatitude();
            Double geoLng = aMapLocation.getLongitude();
            Log.d("坐标是：", "Latitude = " + geoLat.doubleValue() + ", Longitude = " + geoLng.doubleValue());

            // 通过 AMapLocation.getExtras() 方法获取位置的描述信息，包括省、市、区以及街道信息，并以空格分隔。
            String desc = "";
            Bundle locBundle = aMapLocation.getExtras();
            if (locBundle != null) {
                desc = locBundle.getString("desc");
                Log.d("我的位置是：", "desc = " + desc);
            }
            MyApplication.setLatitude(geoLat);
            MyApplication.setLongitude(geoLng);
            MyApplication.setMyLocationName(desc);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMapManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 对定位服务对象进行销毁
        aMapManager.destroy();
    }


}
