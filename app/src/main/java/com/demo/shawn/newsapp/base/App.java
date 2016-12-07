package com.demo.shawn.newsapp.base;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatDelegate;

import com.demo.shawn.newsapp.BuildConfig;
import com.demo.shawn.newsapp.common.Constants;
import com.demo.shawn.newsapp.dao.DaoMaster;
import com.demo.shawn.newsapp.dao.DaoSession;
import com.demo.shawn.newsapp.dao.GreenDbHelper;
import com.demo.shawn.newsapp.di.component.ApplicationComponent;
import com.demo.shawn.newsapp.di.component.DaggerApplicationComponent;
import com.demo.shawn.newsapp.di.module.ApplicationModule;
import com.demo.shawn.newsapp.utils.PreferencesUitls;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by hxy on 2016/12/6 0006.
 * <p>
 * description :
 */

public class App extends Application {
    private static App sAppContext;
    private RefWatcher refWatcher;
    private DaoSession daoSession;
    private ApplicationComponent mApplicationComponent;

    public static RefWatcher getRefWatcher() {
        return sAppContext.refWatcher;
    }

    public static App getAppContext() {
        return sAppContext;
    }

    public static ApplicationComponent getApplicationComponent() {
        return sAppContext.mApplicationComponent;
    }

    public static DaoSession getDaoSession() {
        return sAppContext.daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        initLog();
        initLeakCanary();
        initActivityLifecycleLogs();
        initStrictMode();
        initDayNightMode();
        initDataBase();
        initApplicationComponent();
    }

    //------------------------private method-------------------------------------
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    //开发环境采用严格模式
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
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

    //配置夜间模式或是白天模式
    private void initDayNightMode() {
        if (PreferencesUitls.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initDataBase() {
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
//        默认的实例是DevOpenHelper  但是DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        GreenDbHelper helper = new GreenDbHelper(this, Constants.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
