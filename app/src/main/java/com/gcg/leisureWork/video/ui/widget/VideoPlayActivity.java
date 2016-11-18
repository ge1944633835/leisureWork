package com.gcg.leisureWork.video.ui.widget;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gcg.leisureWork.R;

/**
 * Created by 戈传光 on 2016/8/23.
 * 邮箱 1944633835@qq.com
 */
public class VideoPlayActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SurfaceView surfaceView;
    private Button play,pause;
    private SeekBar seekBar;
    private  MediaPlayer mediaPlayer;
    private Handler handler;
    private  String videoUrl;
    // 底部布局可见
    private boolean isCantouch=true;
    private RelativeLayout bottom;

    private  Button back;

    private ImageView img_pingmu;
    private  boolean isPlaying=false;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 应用运行时，保持屏幕高亮，不锁屏
        setContentView(R.layout.videoplay);
        initView();
        initData();
        initListener();
    }
    private void initData() {
        videoUrl = (String) getIntent().getExtras().get("uri");
        Log.d("xxoo", "initData() returned: " + videoUrl);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载...");
        mProgressDialog.show();
    }
    private void initListener() {
        // surfaceview
        surfaceView.getHolder().setKeepScreenOn(true);
        //surfaceview的生命周期
        surfaceView.getHolder().addCallback(this);
        play.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        surfaceView.setOnClickListener(this);
        back.setOnClickListener(this);
        img_pingmu.setOnClickListener(this);
    }
    private void initView() {
        seekBar = (SeekBar) findViewById(R.id.sk);
        play = (Button) findViewById(R.id.play);
        surfaceView=(SurfaceView) findViewById(R.id.sfv);
        bottom= (RelativeLayout) findViewById(R.id.rl_bottom);
        back= (Button) findViewById(R.id.header_left_btn);
        img_pingmu= (ImageView) findViewById(R.id.bt_full);
        mediaPlayer=new MediaPlayer();
        handler=new Handler();
    }

    void play() {
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            Log.d("MainActivity", "play() returned: " + videoUrl);
            mediaPlayer.setDataSource(videoUrl);
            // 设置play类型
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepare();
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();
            mProgressDialog.dismiss();
            new Thread(updateSeekbar).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void pauseOrPlay() {
        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.pause();
        } else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    Runnable updateSeekbar=new Runnable() {
        @Override
        public void run() {
            try {
                isPlaying = mediaPlayer.isPlaying();
            }
            catch (IllegalStateException e) {
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            }
            if(isPlaying){
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(updateSeekbar, 100);
            }
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        play();
        mediaPlayer.seekTo(0);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if(play.getText().equals("播放")){
                    play.setText("暂停");
                    pauseOrPlay();
                }else {
                    play.setText("播放");
                    pauseOrPlay();
                }
                break;
            case R.id.header_left_btn:
                finish();
                break;
            case R.id.bt_full:
                //当前为竖屏，切换成横屏
                if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                    // 设置横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    img_pingmu.setImageResource(R.drawable.small);
                }else {
                    // 设置竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    img_pingmu.setImageResource(R.drawable.full);
                }
                break;
            case R.id.sfv:

                if( bottom.getVisibility()==View.INVISIBLE &&back.getVisibility()==View.INVISIBLE ){
                    bottom.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                    isCantouch=true;
                }else {
                    bottom.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    isCantouch=false;
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Toast.makeText(VideoPlayActivity.this, ""+newConfig.getLayoutDirection(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(seekBar.getProgress());
        mediaPlayer.start();
    }
}
