package com.gcg.leisureWork.video.ui.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.video.ui.Adapter.ViewPagerAdapter;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
public class VideoFragment extends Fragment {
    private  View mView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.video,container,false);
        mTabLayout= (TabLayout) mView.findViewById(R.id.tablayout_video);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        mViewPager= (ViewPager) mView.findViewById(R.id.viewpager_video);
        mViewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.addFragment(new VideoListFragment(),"在线视频");
        mViewPagerAdapter.addFragment(new LocalVideoListFragment(),"本地视频");
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        return mView;
    }
}
