package com.gcg.leisureWork.video.ui.widget;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.beans.VideoBean;
import com.gcg.leisureWork.video.presenter.VideoPresenter;
import com.gcg.leisureWork.video.presenter.VideoPresenterImpl;
import com.gcg.leisureWork.video.ui.Adapter.LocalVideoAdapter;
import com.gcg.leisureWork.video.ui.Adapter.VideoAdapter;
import com.gcg.leisureWork.video.ui.view.VideoView;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
public class VideoListFragment extends Fragment implements VideoView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "VideoListFragment";
    private VideoView mVideoView = this;
    private Context mContext;
    private View mView;
    private int type;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private VideoPresenter mVideoPresenter;
    private int start = 0;
    private VideoAdapter mAdapter;
    // video数据集
    private List<VideoBean.VideoInfo> mList = new ArrayList<>();
    //第一次加载
    private boolean isFirstLoad = true;
    //本地视频
    private LocalVideoAdapter mLocalVideoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoPresenter = new VideoPresenterImpl(mVideoView, mContext);

        //参考文档--》http://bbs.mob.com/thread-22130-1-1.html
        ShareSDK.initSDK(mContext, "103e7bbf5317e");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.video_list, container, false);
        initView();
        onRefresh();
        return mView;
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycle_view);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                mVideoPresenter.getVideoData(start + 10);
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }
    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void VideoList(final List<VideoBean.VideoInfo> videoInfoList) {
        Log.d(TAG, "VideoList() returned: " + videoInfoList.toString());
        mList.addAll(videoInfoList);
        if (isFirstLoad) {
            mAdapter = new VideoAdapter(mContext, mList);
            mRecyclerView.setAdapter(mAdapter);
            isFirstLoad = false;
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemShareClickListener(new VideoAdapter.OnItemShareClickListener() {
            @Override
            public void onItemShareClick(View view, int position) {
                Toast.makeText(mContext, "--->" + position, Toast.LENGTH_SHORT).show();
                showShare(mList.get(position).getTitle(),mList.get(position).getMp4_url(),mList.get(position).getDescription(),mList.get(position).getCover());
            }
        });
    }

    private void showShare(String title,String titleUrl,String text,String picUrl) {
        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(titleUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        oks.setImagePath("../project6/LeisueWork/src/main/res/drawable/first.jpg");
        oks.setImageUrl(picUrl);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(titleUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(titleUrl);

        // 启动分享GUI
        oks.show(mContext);
    }

    @Override
    public void LocalVideoList(List<LocalVideoBean> localVideoBeanList) {
        Log.d(TAG, "LocalVideoList() returned: " + localVideoBeanList.toString());
        mLocalVideoAdapter = new LocalVideoAdapter(mContext, localVideoBeanList);
        mRecyclerView.setAdapter(mLocalVideoAdapter);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showError() returned: " + error);
        hideProgress();
    }

    @Override
    public void onRefresh() {
        //在线视频
        mList.clear();
        Toast.makeText(getActivity(), "在线视频", Toast.LENGTH_SHORT).show();
        mVideoPresenter.getVideoData(start);

    }
}
