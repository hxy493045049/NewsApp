package com.demo.shawn.newsapp.base;

import android.support.annotation.NonNull;

import com.demo.shawn.newsapp.base.BasePresenter;
import com.demo.shawn.newsapp.base.BaseView;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter {
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {

    }

    @Override
    public void onDestroy() {

    }
}
