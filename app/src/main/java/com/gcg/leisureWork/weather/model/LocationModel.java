package com.gcg.leisureWork.weather.model;

import android.content.Context;

/**
 * Created by 戈传光 on 2016/8/8.
 * 邮箱 1944633835@qq.com
 */
public interface LocationModel {
    void  getCurrentCity(Context context, LocationModelImpl.LocationCallback callback);
}
