package com.demo.shawn.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.base.App;

/**
 * Created by hxy on 2016/12/8 0008.
 * <p>
 * description :
 */

public class NetUtils {
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkErrorThenShowMsg() {
        if (!isNetworkAvailable()) {
            Toast.makeText(App.getAppContext(), App.getResourceString(R.string.net_error), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
