package com.gcg.leisureWork.commons;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class Urls {

    //头条--》 "http://api.jisuapi.com/news/get?channel=头条&start=20&num=10&appkey=019edfd7e2c45c72";

    //新闻的接口
    public  static  String baseUrl_news="http://api.jisuapi.com/";
    public  static  String appkey_news="019edfd7e2c45c72";


    //天气的接口
    //请求实例--》 http://api.jisuapi.com/weather/query?appkey=yourappkey&city=安顺
    public static  String appKey_weather="019edfd7e2c45c72";
    public static  String baseUrl_weather="http://api.jisuapi.com/";

    //全国行政区域划分
    //请求实例--》 http://api.jisuapi.com/area/query?parentid=1&appkey=yourappkey
    public static  String appKey_area="019edfd7e2c45c72";
    public static  String baseUrl_area="http://api.jisuapi.com/";


//    //视频的接口
//    //调用--》https://route.showapi.com/255-1?page=&showapi_appid=12152&title=&type=&showapi_sign=665b99f235879a72aa0da6ac07bf2244
//    public static  String appId_video="23413";
//    public  static  String appkey_video="ca8f06fad7e04b0c87cc616a5dbf139a";
//    public static String baseUrl_video="https://route.showapi.com/";



    /**
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     */
    public static final String Video = "nc/video/list/";
    public static final String VIDEO_CENTER = "/n/";
    public static final String VIDEO_END_URL = "-10.html";
    // 热点视频
    public static final String VIDEO_HOT_ID = "V9LG4B3A0";
    // 娱乐视频
    public static final String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    public static final String VIDEO_FUN_ID = "V9LG4E6VR";
    // 精品视频
    public static final String VIDEO_CHOICE_ID = "00850FRB";

    public static String baseUrl_video="http://c.m.163.com/";



}
