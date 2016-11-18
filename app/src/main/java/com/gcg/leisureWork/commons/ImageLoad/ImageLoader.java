package com.gcg.leisureWork.commons.ImageLoad;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class ImageLoader {

    public  static  void  disPlay(Context context,String path,ImageView imageView){
        Picasso.with(context).load(path).into(imageView);
    }

}
