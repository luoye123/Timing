package com.yy.activity.study.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/8/19.
 */
public class SharedPreferencesUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(MyContant.SPNAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean saveString(String key, String value) {
        editor.putString(key, value);
        // 亿万不要忘了加commit呐~~~！！！！
        return editor.commit();
    }

    public String readString(String key) {
        String str = null;
        str = sp.getString(key, null);
        return str;
    }

}
