package com.gcg.leisureWork.weather.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.CityEvent;
import com.gcg.leisureWork.commons.beans.CityInfo;
import com.gcg.leisureWork.commons.utils.CityFormatUtil;
import com.gcg.leisureWork.commons.utils.MatchPictures;
import com.gcg.leisureWork.weather.presenter.LocationPresenter;
import com.gcg.leisureWork.weather.presenter.LocationPresenterImpl;
import com.gcg.leisureWork.weather.presenter.WeatherPresenter;
import com.gcg.leisureWork.weather.presenter.WeatherPresenterImpl;
import com.gcg.leisureWork.weather.ui.view.WeatherView;

import java.util.Map;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class WeatherFragment extends Fragment implements WeatherView {
    private static final String TAG = "WeatherFragment";
    private Map<String, Integer> map;
    private View mView;
    private WeatherView mWeatherView = this;
    private Context mContext;
    private WeatherPresenter mWeatherPresenter;
    private LocationPresenter mLocationPresenter;
    private String city="上海" ;
    private ImageView ivImage,img_weather, img_today_weather, img_secondDay_weather;
    private TextView tv_tempCurrent, tv_temp_max, tv_temp_min, tv_temp_pm, tv_temp_quality, tv_kongtiao_des, tv_sports_des,
            tv_today_date, tv_today_des, tv_second_date, tv_second_des;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherPresenter = new WeatherPresenterImpl(mWeatherView);
        mLocationPresenter=new LocationPresenterImpl(mContext,mWeatherView);
        mLocationPresenter.getCity();
        EventBus.getDefault().register(this);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.weather, null);
        initView();

        return mView;
    }

    private void initView() {
        mProgressDialog=new ProgressDialog(mContext);
        map = MatchPictures.getPictureRes();
        ivImage= (ImageView) mView.findViewById(R.id.ivImage);
        img_weather = (ImageView) mView.findViewById(R.id.img_weather);
        img_today_weather = (ImageView) mView.findViewById(R.id.img_today_weather);
        img_secondDay_weather = (ImageView) mView.findViewById(R.id.img_secondDay_weather);
        tv_tempCurrent = (TextView) mView.findViewById(R.id.tv_tempCurrent);
        tv_temp_max = (TextView) mView.findViewById(R.id.tv_temp_max);
        tv_temp_min = (TextView) mView.findViewById(R.id.tv_temp_min);
        tv_temp_pm = (TextView) mView.findViewById(R.id.tv_temp_pm);
        tv_temp_quality = (TextView) mView.findViewById(R.id.tv_temp_quality);
        tv_kongtiao_des = (TextView) mView.findViewById(R.id.tv_kongtiao_des);
        tv_sports_des = (TextView) mView.findViewById(R.id.tv_sports_des);
        tv_today_date = (TextView) mView.findViewById(R.id.tv_today_date);
        tv_today_des = (TextView) mView.findViewById(R.id.tv_today_des);
        tv_second_date = (TextView) mView.findViewById(R.id.tv_second_date);
        tv_second_des = (TextView) mView.findViewById(R.id.tv_second_des);
    }

    @Override
    public void setHeadImag() {
        ivImage.setBackgroundResource(R.mipmap.sun_main);
    }

    @Override
    public void showProgress(boolean isShow) {
        if(isShow){
            mProgressDialog.setMessage("正在加载....");
            mProgressDialog.show();
            return;
        }
        mProgressDialog.dismiss();
    }

    @Override
    public void setCity(final String city) {
        EventBus.getDefault().post(new CityEvent(city));
        //定位的城市名为 上海市  而请求的城市名必须为 上海
        mWeatherPresenter.getData(CityFormatUtil.format(city));

    }
    @Override
    public void showLocationCityError(String error) {
        Log.d(TAG, "showLocationCityError() returned: " + error);
    }

    @Override
    public void setCurrentWeatherPicture(String img) {
        //根据返回的 img  确定图片的选择
        img_weather.setImageResource(map.get(img));
    }
    @Override
    public void setCurrentTemp(String temp) {
        Log.d("omg", "setCurrentTemp() returned: " + 23);
        Log.d(TAG, "setCurrentTemp() returned: " + temp);
        tv_tempCurrent.setText(temp);
    }
    @Override
    public void setHighTemp(String highTemp) {
        tv_temp_max.setText(highTemp);
    }
    @Override
    public void setLowTemp(String lowTemp) {
        tv_temp_min.setText(lowTemp);
    }
    @Override
    public void setHumidity(String humidity) {
        tv_temp_pm.setText(humidity);
    }
    @Override
    public void setKongtiaoDes(String ktDes) {
        tv_kongtiao_des.setText(ktDes);
    }
    @Override
    public void setYunDongDes(String ydDes) {
        tv_sports_des.setText(ydDes);
    }
    @Override
    public void setTodayPicture(String picture) {
        img_today_weather.setImageResource(map.get(picture));
    }
    @Override
    public void setTodayWeatherDes(String todayWeatherDes) {
        tv_today_des.setText(todayWeatherDes);
    }
    @Override
    public void setSecondDayPicture(String picture) {
        img_secondDay_weather.setImageResource(map.get(picture));
    }
    @Override
    public void setSecondDayWeatherDes(String secondDayWeatherDes) {
        tv_second_des.setText(secondDayWeatherDes);
    }
    @Override
    public void showErrorToast(String error) {
        Log.d(TAG, "showErrorToast() returned: " + error);
    }


    @Subscribe(threadMode=ThreadMode.MainThread)
    public void  onUserEvent(CityInfo info){
        mWeatherPresenter.getData(info.getCityname());
        EventBus.getDefault().post(new CityEvent(info.getCityname()));
    }

}
