package com.demo.shawn.newsapp.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.annotation.BindValues;
import com.demo.shawn.newsapp.di.component.ActivityComponent;
import com.demo.shawn.newsapp.di.component.DaggerActivityComponent;
import com.demo.shawn.newsapp.di.module.ActivityModule;
import com.demo.shawn.newsapp.utils.MyUtils;
import com.demo.shawn.newsapp.utils.NetUtils;
import com.demo.shawn.newsapp.utils.PreferencesUitls;
import com.demo.shawn.newsapp.utils.RxJavaUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by hxy on 2016/12/7 0007.
 * <p>
 * description :
 */

public abstract class BaseActivity<T extends BaseContract.Presenter> extends AppCompatActivity {
    protected ActivityComponent mActivityComponent;
    protected T mPresenter;
    protected Subscription mSubscription;
    private boolean mIsHasNavigationView;
    private boolean mIsAddedView;//表示是否已经添加过夜间蒙版,蒙版为了防止recreate时的闪屏
    private View mNightView;//夜间模式和白天模式切换的添加的蒙版
    private WindowManager mWindowManager = null;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Class<?> mClass;
    private boolean mIsChangeTheme;

    protected abstract int getLayoutId();//获取布局id

    protected abstract void initInjector();//注入对象

    protected abstract void init();//初始化

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAnnotation();
        NetUtils.isNetworkErrorThenShowMsg();
        initActivityComponent();
        setStatusBarTranslucent();
        setNightOrDayMode();
        setContentView(getLayoutId());

        initInjector();
        ButterKnife.bind(this);
        initToolBar();
        init();
        if (mIsHasNavigationView) {
            initDrawerLayout();
        }
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
        initNightModeSwitch();
    }

    private void initNightModeSwitch() {
        if (mNavigationView != null) {
            MenuItem nightMode = mNavigationView.getMenu().findItem(R.id.nav_night_mode);
            SwitchCompat switchCompat = (SwitchCompat) MenuItemCompat.getActionView(nightMode);
            setCheckedState(switchCompat);
            setCheckedEvent(switchCompat);
        }
    }

    private void setCheckedEvent(SwitchCompat switchCompat) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    changeToNight();
                    PreferencesUitls.saveCurrentDayNightMode(true);
                } else {
                    changeToDay();
                    PreferencesUitls.saveCurrentDayNightMode(false);
                }
                mIsChangeTheme = true;
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mIsHasNavigationView && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void changeToNight() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }

    public void changeToDay() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        initNightView();
        mNightView.setBackgroundResource(android.R.color.transparent);
    }

    private void setCheckedState(SwitchCompat dayNightSwitch) {
        if (PreferencesUitls.isNightMode()) {
            dayNightSwitch.setChecked(true);
        } else {
            dayNightSwitch.setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mIsHasNavigationView) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R
                .string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //再添加一个监听抽屉关闭的观察者
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mClass != null) {
                    Intent intent = new Intent(BaseActivity.this, mClass);
                    // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    mClass = null;
                }

                if (mIsChangeTheme) {
                    mIsChangeTheme = false;
                    getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                    recreate();
                }
            }
        });

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(new NavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
//                        case R.id.nav_news:
//                            mClass = NewsActivity.class;
//                            break;
//                        case R.id.nav_photo:
//                            mClass = PhotoActivity.class;
//                            break;
//                        case R.id.nav_video:
//                            Toast.makeText(BaseActivity.this, "施工准备中...", Toast.LENGTH_SHORT)
// .show();
//                            break;
//                        case R.id.nav_night_mode:
//                            break;
                    }
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (mIsHasNavigationView) {
            overridePendingTransition(0, 0);
        }
//        getWindow().getDecorView().invalidate();
    }

    //toolbar支持
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //初始化白天黑夜模式
    private void setNightOrDayMode() {
        if (PreferencesUitls.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher();
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        removeNightModeMask();

        RxJavaUtils.cancelSubscription(mSubscription);
        MyUtils.fixInputMethodManagerLeak(this);
    }

    private void removeNightModeMask() {
        if (mIsAddedView) {
            // 移除夜间模式蒙板
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
    }

    //给window添加背景view
    private void initNightView() {
        if (mIsAddedView) {
            return;
        }
        // 增加夜间模式蒙板
        WindowManager.LayoutParams nightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams
                        .FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);

        if (mWindowManager == null) {
            mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        }
        mNightView = new View(this);
        mWindowManager.addView(mNightView, nightViewParam);
        mIsAddedView = true;
    }

    //初始化activitycomponent注射器
    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder().applicationComponent(App
                .getApplicationComponent()).activityModule(new ActivityModule(this)).build();
    }

    //获取子类的标识,判断是否需要显示navigation(侧滑栏)
    private void initAnnotation() {
        if (this.getClass().isAnnotationPresent(BindValues.class)) {
            BindValues annotation = this.getClass().getAnnotation(BindValues.class);
            mIsHasNavigationView = annotation.mIsHasNavigationView();
        }
    }

    //适配4.4及以下的沉浸式
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }

}
