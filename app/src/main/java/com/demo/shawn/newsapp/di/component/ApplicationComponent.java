package com.demo.shawn.newsapp.di.component;

import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.di.module.ApplicationModule;
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
    App getApplication();
}
