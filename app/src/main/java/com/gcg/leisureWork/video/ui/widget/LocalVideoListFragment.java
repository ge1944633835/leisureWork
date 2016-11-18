package com.gcg.leisureWork.video.ui.widget;

import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.beans.VideoBean;
import com.gcg.leisureWork.video.presenter.VideoPresenter;
import com.gcg.leisureWork.video.presenter.VideoPresenterImpl;
import com.gcg.leisureWork.video.ui.Adapter.LocalVideoAdapter;
import com.gcg.leisureWork.video.ui.view.VideoView;

import java.util.List;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LocalVideoListFragment extends Fragment implements VideoView{
    private static final String TAG = "LocalVideoListFragment";
    private VideoView mVideoView=this;
    private Context mContext;
    private  View mView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private VideoPresenter mVideoPresenter;
    //本地视频
    private LocalVideoAdapter mLocalVideoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoPresenter=new VideoPresenterImpl(mVideoView,mContext);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.local_video_list,container,false);
        initView();
        mVideoPresenter.getLocalVideoData();
        return mView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initView() {
        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recycle_view);
        mLayoutManager=new GridLayoutManager(getActivity(),1);

        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public void showProgress() {
    }
    @Override
    public void hideProgress() {
    }
    @Override
    public void VideoList(final List<VideoBean.VideoInfo> videoInfoList) {
    }
    @Override
    public void LocalVideoList(final List<LocalVideoBean> localVideoBeanList) {
        Log.d(TAG, "LocalVideoList() returned: " + localVideoBeanList.toString());
        mLocalVideoAdapter=new LocalVideoAdapter(mContext,localVideoBeanList);
        mRecyclerView.setAdapter(mLocalVideoAdapter);
        mLocalVideoAdapter.setOnItemClickListener(new LocalVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(mContext,VideoPlayActivity.class);
                intent.putExtra("uri",localVideoBeanList.get(position).getPath());
                startActivity(intent);
            }
        });
    }
    @Override
    public void showError(String error) {
    }
}
