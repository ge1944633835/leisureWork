package com.gcg.leisureWork.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.gcg.leisureWork.weather.model.LocationModel;
import com.gcg.leisureWork.weather.model.LocationModelImpl;
import com.gcg.leisureWork.weather.ui.view.WeatherView;

/**
 * Created by 戈传光 on 2016/8/8.
 * 邮箱 1944633835@qq.com
 */
public class LocationPresenterImpl implements  LocationPresenter {

    private Context mContext;
    private WeatherView mWeatherView;
    private LocationModel mLocationModel;
    private static final String TAG = "LocationPresenterImpl";

    public LocationPresenterImpl(Context context,WeatherView weatherView) {
        mContext=context;
        mWeatherView = weatherView;
        mLocationModel=new LocationModelImpl();
    }

    @Override
    public void getCity() {
        mLocationModel.getCurrentCity(mContext, new LocationModelImpl.LocationCallback() {
            @Override
            public void onSucess(String city) {
                Log.d(TAG, "onSucess() returned: " +city);
              mWeatherView.setCity(city);
            }
            @Override
            public void onFailed(String error) {
            }
        });
    }
}
