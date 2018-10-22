package com.example.wuye.util;

import android.content.Context;

import com.example.wuye.util.SpUtil;

/**
 * Created by WUYE on 2018/10/21.
 */

public class CacheDataUtil {
    public static void setCache(Context context,String url,String data){
         SpUtil.putString(context,url,data);
    }

    public static  String getCache(Context context,String url){
        return SpUtil.getString(context,url,null);
    }
}
