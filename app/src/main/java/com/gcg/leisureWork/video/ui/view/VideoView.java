package com.gcg.leisureWork.video.ui.view;

import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.beans.VideoBean;

import java.util.List;

/**
 * Created by 戈传光 on 2016/8/18.
 * 邮箱 1944633835@qq.com
 */
public interface VideoView {
        void  showProgress();
        void hideProgress();
        void VideoList(List<VideoBean.VideoInfo> videoInfoList);
        void LocalVideoList(List<LocalVideoBean> localVideoBeanList);
        void  showError(String error);
}
