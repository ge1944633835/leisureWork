package com.gcg.leisureWork.weather.ui.widget.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcg.leisureWork.R;
import com.gcg.leisureWork.commons.beans.CityInfo;

import java.util.List;

/**
 * Created by 戈传光 on 2016/6/23.
 * 邮箱 1944633835@qq.com
 */
public class ProvinceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private  List<CityInfo> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public ProvinceAdapter(Context context, List<CityInfo> list) {
        mList = list;
        mContext=context;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.inflate(R.layout.item_city, parent, false);
            ItemHolder holder = new ItemHolder(view);
            return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).city.setText(mList.get(position).getCityname());
        }
    }
    @Override
    public int getItemCount() {
        return mList.size() ;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView city;

        public ItemHolder(View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.tv_item_city);
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
