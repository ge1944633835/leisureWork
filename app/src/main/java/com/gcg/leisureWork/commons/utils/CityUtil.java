package com.gcg.leisureWork.commons.utils;

import com.gcg.leisureWork.commons.beans.CityInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戈传光 on 2016/8/15.
 * 邮箱 1944633835@qq.com
 */
public class CityUtil {

    private static List<CityInfo> mlist = new ArrayList<>();
    public static List<CityInfo> getCitylist() {
        mlist.add(new CityInfo("北京",1));
        mlist.add(new CityInfo("安徽",2));
        mlist.add(new CityInfo("福建",3));
        mlist.add(new CityInfo("甘肃",4));
        mlist.add(new CityInfo("广东",5));
        mlist.add(new CityInfo("广西",6));
        mlist.add(new CityInfo("贵州",7));

        mlist.add(new CityInfo("海南",8));
        mlist.add(new CityInfo("海北",9));
        mlist.add(new CityInfo("河南",10));
        mlist.add(new CityInfo("黑龙江",11));
        mlist.add(new CityInfo("湖北",12));
        mlist.add(new CityInfo("湖南",13));
        mlist.add(new CityInfo("吉林",14));

        mlist.add(new CityInfo("江苏",15));
        mlist.add(new CityInfo("江西",16));
        mlist.add(new CityInfo("辽宁",17));
        mlist.add(new CityInfo("内蒙古",18));
        mlist.add(new CityInfo("宁夏",19));
        mlist.add(new CityInfo("青海",20));
        mlist.add(new CityInfo("山东",21));

        mlist.add(new CityInfo("山西",22));
        mlist.add(new CityInfo("陕西",23));
        mlist.add(new CityInfo("上海",24));
        mlist.add(new CityInfo("四川",25));
        mlist.add(new CityInfo("天津",26));
        mlist.add(new CityInfo("西藏",27));
        mlist.add(new CityInfo("新疆",28));

        mlist.add(new CityInfo("云南",29));
        mlist.add(new CityInfo("浙江",30));
        mlist.add(new CityInfo("重庆",31));
        mlist.add(new CityInfo("香港",32));
        mlist.add(new CityInfo("澳门",33));
        mlist.add(new CityInfo("台湾",34));


        return mlist;
    }
}
