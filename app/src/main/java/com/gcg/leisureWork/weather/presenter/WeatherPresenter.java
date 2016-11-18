package com.gcg.leisureWork.weather.presenter;

import android.content.Context;

/**
 * Created by 戈传光 on 2016/7/27.
 * 邮箱 1944633835@qq.com
 */
public interface WeatherPresenter {

    void  getCityName(Context context);
    void  getData(String city);
}
