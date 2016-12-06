package com.demo.shawn.newsapp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

import com.demo.shawn.newsapp.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by hxy on 2016/12/6 0006.
 * <p>
 * description :
 */

public class App extends Application {
    private static Context sAppContext;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        App appliction = (App) context.getApplicationContext();
        return appliction.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        initLog();
        initLeakCanary();
        initActivityLifecycleLogs();
        initStrictMode();
    }

    private void initStrictMode() {
//        StrictMode
    }

    //初始化log配置
    private void initLog() {
        if (BuildConfig.LOG_DEBUG) {
            Logger.init("SHAWN").logLevel(LogLevel.FULL);
        } else {
            Logger.init("SHAWN").logLevel(LogLevel.NONE);
        }
    }

    //给全局的activity设置log信息
    private void initActivityLifecycleLogs() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.t(activity.getClass().getSimpleName()).v("onActivityDestroyed");
            }
        });
    }

    //配置内存追踪工具
    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = RefWatcher.DISABLED;
        }
    }
}
