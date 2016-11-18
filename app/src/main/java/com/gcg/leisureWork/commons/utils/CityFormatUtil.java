package com.gcg.leisureWork.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戈传光 on 2016/8/9.
 * 邮箱 1944633835@qq.com
 */
public class CityFormatUtil {
    //上海市--->上海
    public  static  String format(String city){

        List<String> list=new ArrayList<>();
        for (int i=0;i<city.length();i++){
            list.add(String.valueOf(city.charAt(i)));
        }
        if(list.get(city.length()-1).equals("市")){
            list.remove(city.length()-1);
            String newCity="";
            for (int i=0;i<list.size();i++){
                newCity+=list.get(i);
            }
            return newCity.trim();
        }

        return city;
    }
}
