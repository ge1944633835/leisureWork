package com.gcg.leisureWork.commons.beans;

/**
 * Created by 戈传光 on 2016/8/15.
 * 邮箱 1944633835@qq.com
 */
public class CityInfo {
    private  String Cityname;
    private int cityId;

    public CityInfo(String cityname, int cityId) {
        Cityname = cityname;
        this.cityId = cityId;
    }

    public String getCityname() {
        return Cityname;
    }

    public void setCityname(String cityname) {
        Cityname = cityname;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
