package com.demo.shawn.newsapp.di.module;

import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.di.qualifier.ContextLife;
import com.demo.shawn.newsapp.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hxy on 2016/12/7 0007.
 * <p>
 * description :
 */
@Module
public class ApplicationModule {
    private App mApplicationContext;

    public ApplicationModule(App context) {
        mApplicationContext = context;
    }

    @Provides
    @PerApp
    App provideApplicationContext() {
        return mApplicationContext;
    }

}
