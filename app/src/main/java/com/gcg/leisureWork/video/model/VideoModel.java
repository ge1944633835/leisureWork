package com.gcg.leisureWork.video.model;

import android.content.Context;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
public interface VideoModel {

    void getRemoteVideo(int start, VideoModelImpl.RemoteCallback callback);


    void getLocalVideo(Context context, VideoModelImpl.LocalCallback callback);
}
