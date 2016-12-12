package com.demo.shawn.newsapp.di.component;

import android.app.Activity;
import android.content.Context;

import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.di.module.ActivityModule;
import com.demo.shawn.newsapp.di.qualifier.ContextLife;
import com.demo.shawn.newsapp.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by hxy on 2016/12/7 0007.
 * <p>
 * description :
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();
}
