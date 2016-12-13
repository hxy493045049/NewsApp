package com.demo.shawn.newsapp.mvp.impl;

import android.support.annotation.NonNull;

import com.demo.shawn.newsapp.base.impl.BasePresenterImpl;
import com.demo.shawn.newsapp.base.BaseView;
import com.demo.shawn.newsapp.mvp.contract.NewsContract;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class NewPresenterImpl extends BasePresenterImpl implements NewsContract.Presenter  {
    @Override
    public void onChannelDbChanged() {

    }

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
