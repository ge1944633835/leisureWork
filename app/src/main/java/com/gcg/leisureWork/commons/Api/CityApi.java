package com.gcg.leisureWork.commons.Api;

import com.gcg.leisureWork.commons.Retrofit.RetrofitUtils;
import com.gcg.leisureWork.commons.Service.Service;
import com.gcg.leisureWork.commons.Urls;
import com.gcg.leisureWork.commons.beans.City;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 戈传光 on 2016/8/15.
 * 邮箱 1944633835@qq.com
 */
public class CityApi {

    private CityApi(){
    }

    public static Observable<City> getCity(int parentId) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Urls.baseUrl_area);
        Service service = retrofit.create(Service.class);
        Observable<City> observable = service.getCity(parentId,Urls.appKey_weather);
        return observable;
    }

}
