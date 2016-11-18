package com.gcg.leisureWork.weather.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.Api.CityApi;
import com.gcg.leisureWork.commons.beans.City;
import com.gcg.leisureWork.commons.beans.CityInfo;
import com.gcg.leisureWork.weather.ui.widget.Adapter.CityAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 戈传光 on 2016/8/15.
 * 邮箱 1944633835@qq.com
 */
public class CitypickerActivity extends AppCompatActivity {
    private static final String TAG = "CitypickerActivity";
    private Context mContext=this;
    private TextView mTextView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private CityAdapter mCityAdapter;
    private  List<String> cityList;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citypicker);
        initView();
        initData();
    }
    private void initData() {
        int parentId = (int) getIntent().getExtras().get("cityId");
        Log.d(TAG, "initData() returned: " + parentId);
        final Observable<City> observable = CityApi.getCity(parentId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<City, Boolean>() {
                    @Override
                    public Boolean call(City city) {
                        return city.getStatus().equals("0")&&city.getMsg().equals("ok");
                    }
                })
                .map(new Func1<City, List<City.ResultBean>>() {
                    @Override
                    public List<City.ResultBean> call(City city) {
                        return city.getResult();
                    }
                })
                .flatMap(new Func1<List<City.ResultBean>, Observable<City.ResultBean>>() {
                    @Override
                    public Observable<City.ResultBean> call(List<City.ResultBean> resultBeans) {
                        return Observable.from(resultBeans);
                    }
                })
                .subscribe(new Subscriber<City.ResultBean>() {
                    @Override
                    public void onCompleted() {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mCityAdapter=new CityAdapter(mContext,cityList);
                        mRecyclerView.setAdapter(mCityAdapter);
                        mCityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                EventBus.getDefault().post(new CityInfo(cityList.get(position), position));
                                finish();
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() returned: " + e);
                    }
                    @Override
                    public void onNext(City.ResultBean resultBean) {
                       String cityName= resultBean.getName();
                        cityList.add(cityName);
                    }
                });
    }
    private void initView() {
        mProgressBar= (ProgressBar) findViewById(R.id.progress);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView= (RecyclerView) findViewById(R.id.rl);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mToolbar.setTitle("选择城市");
        mProgressBar.setVisibility(View.VISIBLE);
        cityList=new ArrayList<>();
    }
}
