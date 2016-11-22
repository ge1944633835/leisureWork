package com.gcg.leisureWork.news.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.NewsBean;
import com.gcg.leisureWork.commons.beans.NewsDetail;
import com.gcg.leisureWork.news.presenter.NewsPresenter;
import com.gcg.leisureWork.news.presenter.NewsPresenterImpl;
import com.gcg.leisureWork.news.ui.Adapter.DividerItemDecoration;
import com.gcg.leisureWork.news.ui.Adapter.ReCyclerView_Adapter;
import com.gcg.leisureWork.news.ui.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class NewsFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsFragment";
    private int start = 0;
    private String channel;
    private NewsView mNewsView = this;
    private Context mContext;
    private NewsPresenter mNewsPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ReCyclerView_Adapter mReCyclerView_adapter;
    private String mType = NewsListFragment.NEWS_TYPE_TOP;
    public List<NewsBean.ResultBean.ListBean> mList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    boolean isFisrtLoad = true;

    public static NewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mType = getArguments().getString("type");
        channel = mType;
        Log.d("xyz", "onCreate() returned:初次创建---》 " + channel);
        mNewsPresenter = new NewsPresenterImpl(mNewsView, channel, 0);
        Log.d("xyz", "onCreate() returned:初次创建mNewsPresenter---》 " + mNewsPresenter.toString());
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.top_fragment, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        initView(view);
        onRefresh();
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, 1));
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
                    && lastVisibleItem + 1 == mReCyclerView_adapter.getItemCount()
                    && mReCyclerView_adapter.isShowFooter()) {
                //加载更多
                mNewsPresenter.getNewsData(start + 20, channel);
                start+=20;
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
    public void showNewsList(final List<NewsBean.ResultBean.ListBean> list) {
        Log.d("gg", "showNewsList() returned: " + list.size()+"*************"+list.toString());
        mList.addAll(list);
        if (isFisrtLoad) {
            mReCyclerView_adapter = new ReCyclerView_Adapter(mContext, mList);
            mRecyclerView.setAdapter(mReCyclerView_adapter);
            isFisrtLoad=false;
        }else {
            if(list.size()==0||list==null){
                //没有数据了
                mReCyclerView_adapter.isShowFooter(false);
            }
        }
        mReCyclerView_adapter.notifyDataSetChanged();
        mReCyclerView_adapter.setOnItemClickListener(new ReCyclerView_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsBean.ResultBean.ListBean listBean = mList.get(position);
                NewsDetail newsDetail = new NewsDetail(listBean.getPic(), listBean.getContent(), listBean.getTitle());
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsDetail", newsDetail);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void showError(String error) {
        if(start == 0) {
            mReCyclerView_adapter.isShowFooter(false);
            mReCyclerView_adapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

        if (mList != null) {
            mList.clear();
        }
        start=0;
        Log.d("gg", "onCreateView() returned:刷新---》 " +mList.size());
        mNewsPresenter.getNewsData(start, channel);
    }
}
