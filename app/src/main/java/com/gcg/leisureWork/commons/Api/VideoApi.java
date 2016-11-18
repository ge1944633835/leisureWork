package com.gcg.leisureWork.commons.Api;

import com.gcg.leisureWork.commons.Retrofit.RetrofitUtils;
import com.gcg.leisureWork.commons.Service.Service;
import com.gcg.leisureWork.commons.Urls;
import com.gcg.leisureWork.commons.beans.VideoBean;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class VideoApi {
    private VideoApi(){}

    public static Observable<VideoBean> getData(String url) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Urls.baseUrl_video);
        Service service = retrofit.create(Service.class);
        Observable<VideoBean> observable = service.getVideoData(url);
        return observable;
    }
}
