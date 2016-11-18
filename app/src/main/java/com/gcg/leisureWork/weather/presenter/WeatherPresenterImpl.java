package com.gcg.leisureWork.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.gcg.leisureWork.commons.beans.WeatherBean;
import com.gcg.leisureWork.weather.model.WeatherModel;
import com.gcg.leisureWork.weather.model.WeatherModelImpl;
import com.gcg.leisureWork.weather.ui.view.WeatherView;

/**
 * Created by 戈传光 on 2016/7/27.
 * 邮箱 1944633835@qq.com
 */
public class WeatherPresenterImpl implements WeatherPresenter {
    private WeatherView mWeatherView;
    private WeatherModel mWeatherModel;

    public WeatherPresenterImpl(WeatherView weatherView) {
        mWeatherView = weatherView;
        mWeatherModel = new WeatherModelImpl();
    }

    @Override
    public void getData(String city) {
        Log.d("xxoo", "getData() returned: " + "true-->显示进度条");
        Log.d("omg", "onSucess() returned: " + 3);
        mWeatherView.showProgress(true);
        mWeatherView.setHeadImag();
        mWeatherModel.getWeatherData(city, new WeatherModelImpl.WeatherDataCallback() {
            @Override
            public void onSucess(WeatherBean.ResultBean resultBean) {
                Log.d("xxoo", "getData() returned: " + "false-->隐藏进度条");
                mWeatherView.showProgress(false);
//                mWeatherView.setCity(resultBean.getCity());
                mWeatherView.setCurrentTemp(resultBean.getTemp());
                mWeatherView.setCurrentWeatherPicture(resultBean.getImg());
                mWeatherView.setHighTemp(resultBean.getTemphigh());
                mWeatherView.setLowTemp(resultBean.getTemplow());
                mWeatherView.setHumidity(resultBean.getHumidity());
                mWeatherView.setKongtiaoDes(resultBean.getIndex().get(0).getDetail());
                mWeatherView.setYunDongDes(resultBean.getIndex().get(1).getDetail());
                mWeatherView.setTodayPicture(resultBean.getDaily().get(0).getDay().getImg());
                mWeatherView.setTodayWeatherDes("白天" + resultBean.getDaily().get(0).getDay().getWeather() + ",最高气温"
                        + resultBean.getDaily().get(0).getDay().getTemphigh() + "℃" +"\n"+
                        "夜间" + resultBean.getDaily().get(0).getNight().getWeather() +
                        ",最低气温" + resultBean.getDaily().get(0).getNight().getTemplow()+"℃");

                mWeatherView.setSecondDayPicture(resultBean.getDaily().get(1).getDay().getImg());
                mWeatherView.setSecondDayWeatherDes("白天"+resultBean.getDaily().get(1).getDay().getWeather()+",最高气温"
                        +resultBean.getDaily().get(1).getDay().getTemphigh()+ "℃" +"\n"+
                        "夜间"+resultBean.getDaily().get(1).getNight().getWeather()+
                        ",最低气温"+resultBean.getDaily().get(1).getNight().getTemplow()+"℃");
            }

            @Override
            public void onTest(String date) {
            }

            @Override
            public void onFailed(String error) {
                mWeatherView.showErrorToast(error);
            }
        });
    }

    @Override
    public void getCityName(Context context) {
        mWeatherModel.getCurrentCity(context, new WeatherModelImpl.LocationCallback() {
            @Override
            public void onSucess(String city) {
//                mWeatherView.setCity(city);
                Log.d("omg", "onSucess() returned: " + 1);
                getData(city);
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }
}
