package com.gcg.leisureWork.commons.Service;

import com.gcg.leisureWork.commons.beans.City;
import com.gcg.leisureWork.commons.beans.NewsBean;
import com.gcg.leisureWork.commons.beans.VideoBean;
import com.gcg.leisureWork.commons.beans.WeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public interface Service {

    //新闻的service接口
    //"http://api.jisuapi.com/news/get?channel=头条&start=20&num=10&appkey=019edfd7e2c45c72";

    @GET("news/get")
    Observable<NewsBean> getData2(@Query("channel") String channel, @Query("start") int start, @Query("num") int num, @Query("appkey") String appkey);



    //天气的service 接口
   //http://api.jisuapi.com/weather/query?appkey=yourappkey&city=安顺
    @GET("weather/query")
    Observable<WeatherBean> getData(@Query("appkey") String appkey, @Query("city") String city);

    //城市列表的接口
    //http://api.jisuapi.com/area/query?parentid=1&appkey=yourappkey
    @GET("area/query")
    Observable<City> getCity(@Query("parentid") int parentid, @Query("appkey") String appkey);


    //视频的接口
//    http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/0-10.html

    @GET()
    Observable<VideoBean> getVideoData(@Url String url);

}
