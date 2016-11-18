package com.gcg.leisureWork.video.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gcg.leisureWork.commons.Api.VideoApi;
import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.beans.VideoBean;
import com.gcg.leisureWork.commons.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
public class VideoModelImpl implements VideoModel {
    private static final String TAG = "VideoModelImpl";
    private List<VideoBean.VideoInfo> mlist = new ArrayList<>();

    @Override
    public void getRemoteVideo(int start, final RemoteCallback callback) {
        if (mlist.size() != 0) {
            mlist.clear();
        }

        String url = "http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/"+start+"-10.html";
        Log.d(TAG, "getRemoteVideo() returned: " + url);
        Observable<VideoBean> observable = VideoApi.getData(url);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<VideoBean, List<VideoBean.VideoInfo>>() {
                    @Override
                    public List<VideoBean.VideoInfo> call(VideoBean videoBean) {
                        return videoBean.getV9LG4B3A0();
                    }
                })
                .flatMap(new Func1<List<VideoBean.VideoInfo>, Observable<VideoBean.VideoInfo>>() {
                    @Override
                    public Observable<VideoBean.VideoInfo> call(List<VideoBean.VideoInfo> VideoInfos) {
                        return Observable.from(VideoInfos);
                    }
                })
                .filter(new Func1<VideoBean.VideoInfo, Boolean>() {
                    @Override
                    public Boolean call(VideoBean.VideoInfo VideoInfo) {
                        return StringUtils.isNotEmpty(VideoInfo.getMp4_url());
                    }
                })
                .subscribe(new Subscriber<VideoBean.VideoInfo>() {
                    @Override
                    public void onCompleted() {
                        callback.onSucess(mlist);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed("获取数据失败：" + e);
                    }

                    @Override
                    public void onNext(VideoBean.VideoInfo VideoInfo) {
                        mlist.add(VideoInfo);
                    }
                });
    }

    @Override
    public void getLocalVideo(final Context context, final LocalCallback callback) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.onSucess((List<LocalVideoBean>) msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocalVideoBean> videoList = VideoProvider.getVideoList(context);
                Message m = new Message();
                m.obj = videoList;
                handler.sendMessage(m);
            }
        }).start();
    }

    public interface RemoteCallback {
        void onSucess(List<VideoBean.VideoInfo> videoList);

        void onFailed(String error);
    }

    public interface LocalCallback {
        void onSucess(List<LocalVideoBean> videoList);

        void onFailed(String error);
    }
}
