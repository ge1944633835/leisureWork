package com.gcg.leisureWork.news.ui.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.NewsDetail;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by 戈传光 on 2016/7/18.
 * 邮箱 1944633835@qq.com
 */
public class NewsDetailActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private HtmlTextView mHtmlTextView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Log.d("xxoo", "这是详细界面！！！！！！！！！！！");
        initView();
        SetTitleAndContent();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void SetTitleAndContent() {
        NewsDetail newsDetail = (NewsDetail) getIntent().getExtras().getSerializable("newsDetail");
        Log.d("xxoo", "这是标题---》" + newsDetail.getTitle());
        mCollapsingToolbarLayout.setTitle(newsDetail.getTitle());
        //设置图片
        Picasso.with(this).load(newsDetail.getPicUrl()).into(mImageView);
        Log.d("xxoo", "onCreate() returned: 新闻详情---》" + newsDetail.toString()+"---->");
        mHtmlTextView.setHtmlFromString(newsDetail.getContent(), new HtmlTextView.LocalImageGetter());
    }

    private void initView() {
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mImageView= (ImageView) findViewById(R.id.ivImage);
        mHtmlTextView= (HtmlTextView) findViewById(R.id.htNewsContent);
        mCollapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(mToolbar);
        //可以让titlebar显示返回上一级的箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
