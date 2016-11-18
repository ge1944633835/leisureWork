package com.gcg.leisureWork.video.presenter;

import android.content.Context;

import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.beans.VideoBean;
import com.gcg.leisureWork.video.model.VideoModel;
import com.gcg.leisureWork.video.model.VideoModelImpl;
import com.gcg.leisureWork.video.ui.view.VideoView;

import java.util.List;

/**
 * Created by 戈传光 on 2016/8/18.
 * 邮箱 1944633835@qq.com
 */
public class VideoPresenterImpl  implements  VideoPresenter {

    private VideoView mVideoView;
    private VideoModel mVideoModel;
    private Context mContext;

    public VideoPresenterImpl(VideoView videoView,Context context) {
        mVideoView = videoView;
        mContext=context;
        mVideoModel=new VideoModelImpl();
    }
    @Override
    public void getVideoData(int start) {
        mVideoView.showProgress();
            //在线视频
            mVideoModel.getRemoteVideo(start, new VideoModelImpl.RemoteCallback() {
                @Override
                public void onSucess(List<VideoBean.VideoInfo> videoList) {
                    mVideoView.hideProgress();
                    mVideoView.VideoList(videoList);
                }
                @Override
                public void onFailed(String error) {
                    mVideoView.showError(error);
                }
            });
    }
    @Override
    public void getLocalVideoData() {
        //本地视频
        mVideoModel.getLocalVideo(mContext, new VideoModelImpl.LocalCallback() {
            @Override
            public void onSucess(List<LocalVideoBean> videoList) {
                mVideoView.hideProgress();
                mVideoView.LocalVideoList(videoList);
            }
            @Override
            public void onFailed(String error) {
            }
        });
    }
}
