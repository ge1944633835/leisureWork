package com.gcg.leisureWork.weather.event;

/**
 * Created by 戈传光 on 2016/8/4.
 * 邮箱 1944633835@qq.com
 */
public class Event {
    private  String city;


    public Event(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
