package com.gcg.leisureWork.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.about.widget.AboutFragment;
import com.gcg.leisureWork.commons.beans.CityEvent;
import com.gcg.leisureWork.main.presenter.MainPresenter;
import com.gcg.leisureWork.main.presenter.MainPresenterImpl;
import com.gcg.leisureWork.main.view.MainView;
import com.gcg.leisureWork.news.ui.widget.NewsListFragment;
import com.gcg.leisureWork.video.ui.widget.VideoFragment;
import com.gcg.leisureWork.weather.ui.widget.ProvincepickerActivity;
import com.gcg.leisureWork.weather.ui.widget.WeatherFragment;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private   Toolbar toolbar;
    private ImageButton pickcity;
    private   FloatingActionButton fab;
    private   DrawerLayout drawer;
    private   NavigationView navigationView;
    private  ActionBarDrawerToggle toggle;
    private MainView mMainView=this;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        mMainPresenter=new MainPresenterImpl(mMainView);
        switch2news();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread) //在ui线程执行
     public void onUserEvent(CityEvent event) {
        toolbar.setTitle(event.getCity());

    }


    private void initListener() {
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("LeisureWork");
        pickcity= (ImageButton) findViewById(R.id.btn_pickcity);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
    }
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news) {
            mMainPresenter.switchNavigation(R.id.news);
        } else if (id == R.id.images) {
            mMainPresenter.switchNavigation(R.id.images);
        } else if (id == R.id.weather) {
            mMainPresenter.switchNavigation(R.id.weather);
        } else if (id == R.id.other) {
            mMainPresenter.switchNavigation(R.id.other);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void switch2news() {
        pickcity.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsListFragment()).commit();
    }
    @Override
    public void switch2image() {
        pickcity.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new VideoFragment()).commit();

    }
    @Override
    public void switch2weather() {
        pickcity.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new WeatherFragment()).commit();
        pickcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ProvincepickerActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void switch2other() {
        pickcity.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new AboutFragment()).commit();
    }


}
