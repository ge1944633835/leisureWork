package com.gcg.leisureWork.weather.model;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gcg.leisureWork.commons.Api.WeatherApi;
import com.gcg.leisureWork.commons.beans.WeatherBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class WeatherModelImpl implements WeatherModel,BDLocationListener {
   private LocationClient mLocationClient = null;
    private  String cityName;
    private  String LocError;
    private  LocationCallback mCallback;

    @Override
    public void getWeatherData(String city, final WeatherDataCallback callback) {


        Log.d("xxoo", "getWeatherData() returned: " + city);
        Observable<WeatherBean> observable = WeatherApi.getData(city);
        Log.d("xxoo", "getWeatherData() returned: " + observable.toString());
        Log.d("xxoo", "getWeatherData() returned: " + "----1");

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<WeatherBean, WeatherBean.ResultBean>() {
                    @Override
                    public WeatherBean.ResultBean call(WeatherBean weatherBean) {
                        Log.d("xxoo", "getWeatherData() returned: " + weatherBean.toString());
                        return weatherBean.getResult();
                    }
                })
                .subscribe(new Subscriber<WeatherBean.ResultBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed("获取数据失败;" + e);
                    }
                    @Override
                    public void onNext(WeatherBean.ResultBean resultBean) {
                        callback.onSucess(resultBean);
                    }
                });
    }

    @Override
    public void getCurrentCity(Context context,final LocationCallback callback) {
        mCallback=callback;
        mLocationClient = new LocationClient(context);     //声明LocationClient类
        mLocationClient.registerLocationListener(this);    //注册监听函数
        initLocation();
        mLocationClient.start();
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Log.d("xxoo", "onReceiveLocation() returned: " + bdLocation.getLocType());
        if(bdLocation.getCity()!=null){
            cityName= bdLocation.getCity();
            mCallback.onSucess(cityName);
            Log.d("xxoo", "onReceiveLocation() returned: " + bdLocation.getCity());
        }else if(bdLocation.getLocType() == BDLocation.TypeNetWorkException){
            LocError="网络不同导致定位失败，请检查网络是否通畅";
            mCallback.onFailed(LocError);
        }else if(bdLocation.getLocType() == BDLocation.TypeCriteriaException){
            LocError="无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机";
            mCallback.onFailed(LocError);
        }
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }



    public interface WeatherDataCallback {
        void onSucess(WeatherBean.ResultBean resultBean);
        void onTest(String date);
        void onFailed(String error);
    }

    public interface LocationCallback {
        void onSucess(String city);
        void onFailed(String error);
    }

}
