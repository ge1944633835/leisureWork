package com.gcg.leisureWork.weather.ui.view;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public interface WeatherView {

    void  setHeadImag();

    void  showProgress(boolean isShow);

    void setCity(String city);
    void showLocationCityError(String error);

    void setCurrentWeatherPicture(String img);

    void setCurrentTemp(String temp);

    void setHighTemp(String highTemp);

    void setLowTemp(String lowTemp);

    void setHumidity(String humidity);

    void setKongtiaoDes(String ktDes);

    void setYunDongDes(String ydDes);

    void setTodayPicture(String picture);

    void setTodayWeatherDes(String todayWeatherDes);

    void setSecondDayPicture(String picture);

    void setSecondDayWeatherDes(String secondDayWeatherDes);

    void showErrorToast(String error);

}
