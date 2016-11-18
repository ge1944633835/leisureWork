package com.gcg.leisureWork.commons.Api;

import com.gcg.leisureWork.commons.Retrofit.RetrofitUtils;
import com.gcg.leisureWork.commons.Service.Service;
import com.gcg.leisureWork.commons.Urls;
import com.gcg.leisureWork.commons.beans.WeatherBean;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class WeatherApi {
    private  WeatherApi(){}

    public static Observable<WeatherBean> getData(String city) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Urls.baseUrl_weather);
        Service service = retrofit.create(Service.class);
        Observable<WeatherBean> observable = service.getData(Urls.appKey_weather, city);
        return observable;
    }
}
