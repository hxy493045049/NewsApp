package com.demo.shawn.newsapp.di.component;

import android.content.Context;

import com.demo.shawn.newsapp.di.module.ApplicationModule;
import com.demo.shawn.newsapp.di.qualifier.ContextLife;
import com.demo.shawn.newsapp.di.scope.PerApp;

import dagger.Component;

/**
 * Created by hxy on 2016/12/6 0006.
 * <p>
 * description :
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}
