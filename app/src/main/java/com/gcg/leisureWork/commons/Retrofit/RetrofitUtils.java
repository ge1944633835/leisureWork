package com.gcg.leisureWork.commons.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class RetrofitUtils {



    private  RetrofitUtils(){};

    public  static Retrofit  getRetrofit(String baseUrl){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }




}
