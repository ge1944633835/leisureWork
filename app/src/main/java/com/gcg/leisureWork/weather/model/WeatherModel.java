package com.gcg.leisureWork.weather.model;

import android.content.Context;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public interface WeatherModel {
    void  getCurrentCity(Context context, WeatherModelImpl.LocationCallback callback);
    void  getWeatherData(String city, WeatherModelImpl.WeatherDataCallback callback);
}
