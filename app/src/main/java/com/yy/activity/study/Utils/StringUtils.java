package com.yy.activity.study.Utils;

import java.text.SimpleDateFormat;

/**
 * Created by hasee on 2017/5/28.
 */

public class StringUtils {


    //获取当前的时间
    public static String gettime(){
        SimpleDateFormat sDateFormat  =  new SimpleDateFormat("hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

}
