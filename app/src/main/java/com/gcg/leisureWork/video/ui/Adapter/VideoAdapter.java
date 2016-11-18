package com.gcg.leisureWork.video.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.VideoBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by 戈传光 on 2016/6/23.
 * 邮箱 1944633835@qq.com
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "VideoAdapter";
    private Context mContext;
    private List<VideoBean.VideoInfo> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private  OnItemShareClickListener mOnItemShareClickListener;
    private  OnItemVideoClickListener mOnItemVideoClickListener;

    private int other_item = 0;
    private int footer_item = 1;
    private boolean showFooter = true;

    public VideoAdapter(Context context, List<VideoBean.VideoInfo> list) {
        Log.d(TAG, "onCreateViewHolder() returned: " + "适配器初始化了？");
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "onCreateViewHolder() returned: " + "适配器初始化---" + mList.size());
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        // 最后一个item设置为footerView
        if(!showFooter){
            return other_item;
        }
        if(position+1==getItemCount()){
            return  footer_item;
        }else {
            return other_item;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() returned: " + "适配器设置了holder");
        View view;
        if(viewType==footer_item){
            view = mInflater.inflate(R.layout.footer, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            FooterHolder holder = new FooterHolder(view);
            return holder;
        }else {
            view = mInflater.inflate(R.layout.item_videolist, parent, false);
            ItemHolder holder = new ItemHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder() returned: " + "设置了吗");
        if (holder instanceof ItemHolder) {

            ((ItemHolder) holder).mJCVideoPlayer.setUp(mList.get(position).getMp4_url(),mList.get(position).getCover(),mList.get(position).getTitle());
            Log.d(TAG, "onBindViewHolder() returned: 播放的次数都是0？？ " +mList.get(position).getPlayCount() );
            ((ItemHolder) holder).tv_play_time.setText(mList.get(position).getPlayCount()+"");
            ((ItemHolder) holder).tv_form.setText(mList.get(position).getTopicName());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount() returned: " + "数据集的大小：" + mList.size());
        int  begin=showFooter? 1:0;
        if(mList==null){
            return begin;
        }
        return mList.size()+begin;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    //video图片的点击事件
    public void setOnItemVideoClickListener(OnItemVideoClickListener onItemVideoClickListener) {
        this.mOnItemVideoClickListener = onItemVideoClickListener;
    }
    //分享的点击事件
    public void setOnItemShareClickListener(OnItemShareClickListener onItemShareClickListener) {
        this.mOnItemShareClickListener = onItemShareClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public interface OnItemVideoClickListener {
        public void onItemVideoClick(View view, int position);
    }
    public interface OnItemShareClickListener {
        public void onItemShareClick(View view, int position);
    }

    public void isShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }
    public boolean isShowFooter() {
        return this.showFooter;
    }

    //底部布局holder
    private class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
    //其他布局holder
    private class ItemHolder extends RecyclerView.ViewHolder {

        private JCVideoPlayer mJCVideoPlayer;
        private ImageView iv_logo;
        private TextView tv_form;
        private  TextView tv_play_time;
        private  ImageView share;

        public ItemHolder(View itemView) {
            super(itemView);
            mJCVideoPlayer = (JCVideoPlayer) itemView.findViewById(R.id.videoplayer);
            iv_logo= (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_form= (TextView) itemView.findViewById(R.id.tv_from);
            tv_play_time= (TextView) itemView.findViewById(R.id.tv_play_time);
            share= (ImageView) itemView.findViewById(R.id.img_share);


            mJCVideoPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(mOnItemVideoClickListener!=null){
                       mOnItemVideoClickListener.onItemVideoClick(v, getPosition());
                   }
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemShareClickListener!=null){
                        mOnItemShareClickListener.onItemShareClick(v, getPosition());
                    }
                }
            });
        }

    }
}
