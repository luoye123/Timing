package com.yy.activity.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yy.activity.study.Service.TimeService;
import com.yy.activity.study.Utils.MyContant;
import com.yy.activity.study.Utils.SharedPreferencesUtil;
import com.yy.activity.study.Utils.StringUtils;

/**
 * Created by hasee on 2017/5/6.
 */
public class TimeActivity extends AppCompatActivity {

    public static String TIME_CHANGED_ACTION = "com.yy.time.TIME_CHANGED_ACTION";
    public static TextView tv_time;
    private SharedPreferencesUtil util;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        tv_time= (TextView) findViewById(R.id.tv_time);
        util=new SharedPreferencesUtil(this);
        util.saveString(MyContant.STARTTIME, StringUtils.gettime());
        startService(new Intent(this, TimeService.class));

    }
}
