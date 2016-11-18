package com.gcg.leisureWork.commons.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Generator {
    public static final String API_BASE_URL = "https://api.heweather.com";  
  
    public static int DEFAULT_TIMEOUT = 10;  
  
  
    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)  
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)  
            .build();  
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)  
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)  
            .build();  
  
    public static void ChangeURl(String url) {  
  
  
        retrofit = new Retrofit.Builder()  
                .baseUrl(url)  
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)  
                .build();  
    }  
  
  
    public static <S> S createService(Class<S> serviceClass) {  
        return retrofit.create(serviceClass);  
    }  
  
  
} 