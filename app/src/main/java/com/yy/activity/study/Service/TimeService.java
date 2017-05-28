package com.yy.activity.study.Service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.yy.activity.study.TimeActivity;
import com.yy.activity.study.Utils.MyContant;
import com.yy.activity.study.Utils.SharedPreferencesUtil;
import com.yy.activity.study.Utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    private String TAG = "TimeService";
    private Timer timer = null;
    private Intent timeIntent = null;
    private SharedPreferencesUtil util;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"TimeService->onCreate");
        //初始化
        this.init();
        //定时器发送广播
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //发送广播
                sendTimeChangedBroadcast();
            }
        }, 1000,1000);
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"TimeService->onBind");
        return null;
    }
    /**
     * 相关变量初始化
     */
    private void init(){
        util=new SharedPreferencesUtil(this);
        timer = new Timer();
        timeIntent = new Intent();
    }

    /**
     * 发送广播，通知UI层时间已改变
     */
    private void sendTimeChangedBroadcast(){
        try {
            timeIntent.putExtra("time",getTime());
            timeIntent.setAction(TimeActivity.TIME_CHANGED_ACTION);
            //发送广播，通知UI层时间改变了
            sendBroadcast(timeIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取最新时间
     * @return
     */
    private String getTime() throws ParseException {

        String time;
        time=getsubtract(util.readString(MyContant.STARTTIME));
        return time;
    }

    //时间相减  得到计时时间
    public String getsubtract(String starttime) throws ParseException {

        SimpleDateFormat myFormatter = new SimpleDateFormat( "hh:mm:ss");
        String newtime= StringUtils.gettime();
        Date date= myFormatter.parse(newtime);
        Date mydate= myFormatter.parse(starttime);
        int sec= (int) ((date.getTime()-mydate.getTime())/1000);
        int min=sec/60;
        int hour=min/60;

        if (sec >= 60) {
            sec = (sec % 60);
        }

        if (min >= 60) {
            min = (min % 60);
        }
        String hString;
        String mString;
        String string;
        if (hour < 10) {
            hString = "0" + String.valueOf(hour);
        } else {
            hString = String.valueOf(hour);
        }
        if (min < 10) {
            mString = "0" + String.valueOf(min);
        } else {
            mString = String.valueOf(min);
        }
        if (sec < 10) {
            string = "0" + String.valueOf(sec);
        } else {
            string = String.valueOf(sec);
        }

        return hString + ":" + mString + ":" + string;
    }

    @Override
    public ComponentName startService(Intent service) {
        Log.i(TAG,"TimeService->startService");
        return super.startService(service);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"TimeService->onDestroy");
    }
}
