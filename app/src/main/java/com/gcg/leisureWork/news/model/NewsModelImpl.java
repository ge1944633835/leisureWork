package com.gcg.leisureWork.news.model;

import android.util.Log;

import com.gcg.leisureWork.commons.Api.NewsApi;
import com.gcg.leisureWork.commons.beans.NewsBean;
import com.gcg.leisureWork.commons.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class NewsModelImpl implements NewsModel {
    private List<NewsBean.ResultBean.ListBean> mList = new ArrayList<>();


    @Override
    public void getNewsData(String channel, int start, final LoadCallBack callBack) {
        if(mList.size()!=0){
            mList.clear();
        }
        Observable<NewsBean> observable = NewsApi.getData(channel,start);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsBean, List<NewsBean.ResultBean.ListBean>>() {
                    @Override
                    public List<NewsBean.ResultBean.ListBean> call(NewsBean newsBean) {
                        return newsBean.getResult().getList();
                    }
                })
                .flatMap(new Func1<List<NewsBean.ResultBean.ListBean>, Observable<NewsBean.ResultBean.ListBean>>() {
                    @Override
                    public Observable<NewsBean.ResultBean.ListBean> call(List<NewsBean.ResultBean.ListBean> listBeans) {
                        return Observable.from(listBeans);
                    }
                })
                .filter(new Func1<NewsBean.ResultBean.ListBean, Boolean>() {
                    @Override
                    public Boolean call(NewsBean.ResultBean.ListBean listBean) {
                        boolean empty = StringUtils.isEmpty(listBean.getPic());
                        return !empty;
                    }
                })
                .subscribe(new Subscriber<NewsBean.ResultBean.ListBean>() {
                    @Override
                    public void onCompleted() {
                        callBack.sucess(mList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed("获取数据失败：" + e);
                    }

                    @Override
                    public void onNext(NewsBean.ResultBean.ListBean listBean) {
                        mList.add(listBean);
                        Log.d("xxoo", "onNext() returned: " + mList.size());

                    }
                });
    }

    public interface LoadCallBack {

        void  sucess(List<NewsBean.ResultBean.ListBean> listBean);
        void failed(String error);
    }

}
