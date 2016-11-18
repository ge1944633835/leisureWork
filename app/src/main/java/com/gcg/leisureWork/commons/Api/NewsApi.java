package com.gcg.leisureWork.commons.Api;

import com.gcg.leisureWork.commons.Retrofit.RetrofitUtils;
import com.gcg.leisureWork.commons.Service.Service;
import com.gcg.leisureWork.commons.Urls;
import com.gcg.leisureWork.commons.beans.NewsBean;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class NewsApi {

    private NewsApi() {
    }

    public static Observable<NewsBean> getData(String channel, int start) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Urls.baseUrl_news);
        Service service = retrofit.create(Service.class);
        Observable<NewsBean> observable = service.getData2(channel, start, 20, Urls.appkey_news);
        return observable;
    }
}
