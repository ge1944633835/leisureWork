package com.gcg.leisureWork.news.model;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public interface NewsModel {

   void getNewsData(String channel, int start, NewsModelImpl.LoadCallBack callBack);

//   void getNewsData(String  url,NewsModelImpl.LoadCallBack callBack);

}
