package com.gcg.leisureWork.video.ui.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.LocalVideoBean;
import com.gcg.leisureWork.commons.utils.VideoUtils;

import java.util.List;

/**
 * Created by 戈传光 on 2016/6/23.
 * 邮箱 1944633835@qq.com
 */
public class LocalVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "LocalVideoAdapter";
    private Context mContext;
    private List<LocalVideoBean> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;


    public LocalVideoAdapter(Context context, List<LocalVideoBean> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() returned: " + "适配器设置了holder");
        View view = mInflater.inflate(R.layout.item_localvideolist, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder() returned: " + "设置了吗");
        if (holder instanceof ItemHolder) {
            //显示recyclerView的内容
            ((ItemHolder) holder).date.setText(mList.get(position).getDuration()+"");
            ((ItemHolder) holder).title.setText(mList.get(position).getTitle());

            final Handler handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    ((ItemHolder) holder).video.setImageBitmap((Bitmap) msg.obj);
                }
            };
            new  Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = VideoUtils.getVideoThumbnail(mList.get(position).getPath());
                    Message message=new Message();
                    message.obj=bitmap;
                    handler.sendMessage(message);
                }
            }).start();
        }
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }



    //其他布局holder
    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView date;
        private ImageView video;

        public ItemHolder(View itemView) {
            super(itemView);
            video = (ImageView) itemView.findViewById(R.id.img_localvideo);
            title = (TextView) itemView.findViewById(R.id.tv_localVideo_title);
            date = (TextView) itemView.findViewById(R.id.tv_localVideo_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
}
