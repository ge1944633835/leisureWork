package com.gcg.leisureWork.weather.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.CityInfo;
import com.gcg.leisureWork.commons.utils.CityUtil;
import com.gcg.leisureWork.weather.ui.widget.Adapter.ProvinceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戈传光 on 2016/8/15.
 * 邮箱 1944633835@qq.com
 */
public class ProvincepickerActivity extends AppCompatActivity {

    private Context mContext=this;
    private TextView mTextView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProvinceAdapter mCityAdapter;
    private List<CityInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provincepicker);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mCityAdapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(mContext,CitypickerActivity.class);
                int cityId=  mList.get(position).getCityId();
                intent.putExtra("cityId",cityId);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initData() {
        mList=new ArrayList<>();
        mList=CityUtil.getCitylist();
        mCityAdapter=new ProvinceAdapter(mContext, mList);
        mRecyclerView.setAdapter(mCityAdapter);
        mToolbar.setTitle("选择省份");
    }
    private void initView() {
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView= (RecyclerView) findViewById(R.id.rl);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }
}
