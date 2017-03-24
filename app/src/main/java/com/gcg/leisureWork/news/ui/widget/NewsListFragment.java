package com.gcg.leisureWork.news.ui.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.news.presenter.NewsPresenter;
import com.gcg.leisureWork.news.ui.Adapter.ViewPagerAdapter;
/**
 * Created by 戈传光 on 2016/7/14.
 * 邮箱 1944633835@qq.com
 */
public class NewsListFragment extends Fragment {

    public static final String NEWS_TYPE_TOP = "头条";
    public static final String NEWS_TYPE_YULE = "娱乐";
    public static final String NEWS_TYPE_SPORTS = "体育";
    public static final String NEWS_TYPE_NBA = "NBA";

    private TabLayout mTabLayout;
    private NewsPresenter mNewsPresenter;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
//    private List<NewsFragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.news,null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        initView(view);
        return view;
    }


    private void initView(View view) {
        mTabLayout= (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
        mTabLayout.addTab(mTabLayout.newTab().setText("娱乐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("体育"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NBA"));
        mViewPager= (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.addFragment(NewsFragment.newInstance(NEWS_TYPE_TOP), "头条");
        mViewPagerAdapter.addFragment(NewsFragment.newInstance(NEWS_TYPE_YULE), "娱乐");
        mViewPagerAdapter.addFragment(NewsFragment.newInstance(NEWS_TYPE_SPORTS), "体育");
        mViewPagerAdapter.addFragment(NewsFragment.newInstance(NEWS_TYPE_NBA), "NBA");

        mViewPager.setAdapter(mViewPagerAdapter);
    }
}
