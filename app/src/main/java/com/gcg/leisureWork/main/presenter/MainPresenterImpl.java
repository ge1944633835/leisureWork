package com.gcg.leisureWork.main.presenter;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.main.view.MainView;

/**
 * Created by 戈传光 on 2016/7/13.
 * 邮箱 1944633835@qq.com
 */
public class MainPresenterImpl  implements MainPresenter {
    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
    }
    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.news :
                mMainView.switch2news();
                break;
            case R.id.images :
                mMainView.switch2image();
                break;
            case R.id.weather :
                mMainView.switch2weather();
                break;
            case R.id.other :
                mMainView.switch2other();
                break;
            default:
                mMainView.switch2news();
                break;
        }
    }
}
