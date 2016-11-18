package com.gcg.leisureWork.news.ui.view;


import com.gcg.leisureWork.commons.beans.NewsBean;

import java.util.List;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public interface NewsView {

    void  showProgress();
    void  hideProgress();
    void showNewsList(List<NewsBean.ResultBean.ListBean> listBean);
    void showError(String s);

}
