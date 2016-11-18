package com.gcg.leisureWork.commons.utils;

import com.gcg.leisureWork.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 戈传光 on 2016/7/28.
 * 邮箱 1944633835@qq.com
 */
public class MatchPictures {

    private MatchPictures(){};
    private static Map<String, Integer> mMap = new HashMap<>();
    public static Map<String, Integer> getPictureRes() {
        mMap.put("0", R.drawable.zero);
        mMap.put("1", R.drawable.one);
        mMap.put("2", R.drawable.two);
        mMap.put("3", R.drawable.three);
        mMap.put("4", R.drawable.four);
        mMap.put("5", R.drawable.five);
        mMap.put("6", R.drawable.six);
        mMap.put("7", R.drawable.seven);
        mMap.put("8", R.drawable.eight);
        mMap.put("9", R.drawable.nine);
        mMap.put("10", R.drawable.ten);
        mMap.put("11", R.drawable.eleven);
        mMap.put("12", R.drawable.twelve);
        mMap.put("13", R.drawable.thirteen);
        mMap.put("14", R.drawable.fourteen);
        mMap.put("15", R.drawable.fifteen);
        mMap.put("16", R.drawable.sixteen);
        mMap.put("17", R.drawable.seventeen);
        mMap.put("18", R.drawable.eighteen);
        mMap.put("19", R.drawable.nineteen);
        mMap.put("20", R.drawable.twenty);
        mMap.put("21", R.drawable.twenty1);
        mMap.put("22", R.drawable.twenty2);
        mMap.put("23", R.drawable.twenty3);
        mMap.put("24", R.drawable.twenty4);
        mMap.put("25", R.drawable.twenty5);
        mMap.put("26", R.drawable.twenty6);
        mMap.put("27", R.drawable.twenty7);
        mMap.put("28", R.drawable.twenty8);
        mMap.put("29", R.drawable.twenty9);
        mMap.put("30", R.drawable.thirty);
        mMap.put("31", R.drawable.thirty1);
        mMap.put("32", R.drawable.thirty2);
        mMap.put("49", R.drawable.forty9);
        mMap.put("53", R.drawable.fifty3);
        mMap.put("54", R.drawable.fifty4);
        mMap.put("55", R.drawable.fifty5);
        mMap.put("56", R.drawable.fifty6);
        mMap.put("57", R.drawable.fifty7);
        mMap.put("58", R.drawable.fifty8);
        mMap.put("99", R.drawable.ninety9);
        mMap.put("301", R.drawable.three01);
        mMap.put("302", R.drawable.three02);
        return mMap;
    }

}
