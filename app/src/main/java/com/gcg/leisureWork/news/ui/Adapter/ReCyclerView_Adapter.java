package com.gcg.leisureWork.news.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.NewsBean;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

/**
 * Created by 戈传光 on 2016/6/23.
 * 邮箱 1944633835@qq.com
 */
public class ReCyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private  List<NewsBean.ResultBean.ListBean> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private String temp;
    private int other_item = 0;
    private int footer_item = 1;
    private boolean showFooter = true;

    public ReCyclerView_Adapter(Context context, List<NewsBean.ResultBean.ListBean> list) {
        mList = list;
        mContext=context;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        // 最后一个item设置为footerView
        if (!showFooter) {
            return other_item;
        }
        if (position + 1 == getItemCount()) {
            return footer_item;
        } else {
            return other_item;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("MainActivity", "onCreateViewHolder() returned: " + viewType);
        View view;
        if (viewType == footer_item) {
            view = mInflater.inflate(R.layout.footer, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            FooterHolder holder = new FooterHolder(view);
            return holder;
        } else {
            view = mInflater.inflate(R.layout.news_item, parent, false);
            ItemHolder holder = new ItemHolder(view);
            return holder;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((ItemHolder) holder).tvDesc.setHtmlFromString(mList.get(position).getContent(), new HtmlTextView.LocalImageGetter());
            Picasso.with(mContext)
                    .load(mList.get(position).getPic())
                    .into(((ItemHolder) holder).mImageView);
        }
    }
    @Override
    public int getItemCount() {
        int begin = showFooter ? 1 : 0;
        if (mList == null) {
            return begin;
        }
        return mList.size() + begin;
    }
    public void isShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.showFooter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    private class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private HtmlTextView tvDesc;
        private ImageView mImageView;

        public ItemHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (HtmlTextView) itemView.findViewById(R.id.tvDesc);
            mImageView= (ImageView) itemView.findViewById(R.id.imgNews);
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
