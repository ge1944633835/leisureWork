package com.gcg.leisureWork.news.presenter;

import android.util.Log;

import com.gcg.leisureWork.commons.beans.NewsBean;
import com.gcg.leisureWork.news.model.NewsModel;
import com.gcg.leisureWork.news.model.NewsModelImpl;
import com.gcg.leisureWork.news.ui.view.NewsView;

import java.util.List;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class NewsPresenterImpl implements NewsPresenter {
    private NewsView mNewsView;
    private NewsModel mNewsModel;
    private String channel;
    private int start;
    private String url;

    public NewsPresenterImpl(NewsView newsView, String channel, int start) {
        mNewsView = newsView;
        this.channel = channel;
        this.start = start;

        mNewsModel = new NewsModelImpl();
    }

    public NewsPresenterImpl(NewsView newsView, String url) {
        mNewsView = newsView;
        this.url = url;
        mNewsModel = new NewsModelImpl();
        mNewsView.showProgress();
    }

    @Override
    public void getNewsData(int start,String channel) {
        if(start==0){
            mNewsView.showProgress();
        }
        mNewsModel.getNewsData(channel, start, new NewsModelImpl.LoadCallBack() {
            @Override
            public void sucess(List<NewsBean.ResultBean.ListBean> listBean) {
                mNewsView.showNewsList(listBean);
                mNewsView.hideProgress();
            }
            @Override
            public void failed(String error) {
                mNewsView.showError(error);
            }
        });
    }
}
