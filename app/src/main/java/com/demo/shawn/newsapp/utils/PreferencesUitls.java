package com.demo.shawn.newsapp.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.common.Constants;

/**
 * Created by hxy on 2016/12/6 0006.
 * <p>
 * description :
 */

public class PreferencesUitls {

    //判断是夜间模式还是白天模式
    public static boolean isNightMode() {
        SharedPreferences preferences = App.getAppContext().getSharedPreferences(
                Constants.SHARES_COLOURFUL_NEWS, Activity.MODE_PRIVATE);
        return preferences.getBoolean(Constants.NIGHT_THEME_MODE, false);
    }

    public static void saveCurrentDayNightMode(boolean isNight){
        SharedPreferences preferences = App.getAppContext().getSharedPreferences(
                Constants.SHARES_COLOURFUL_NEWS, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.NIGHT_THEME_MODE, isNight);
        editor.apply();
    }
}
