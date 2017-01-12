package com.demo.shawn.newsapp.base.impl;

import android.support.annotation.NonNull;

import com.demo.shawn.newsapp.base.BaseContract;
import com.demo.shawn.newsapp.data.RequestCallBack;
import com.demo.shawn.newsapp.utils.MyUtils;

import rx.Subscription;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public abstract class BasePresenterImpl<T extends BaseContract.View, E> implements BaseContract.Presenter,
        RequestCallBack<E> {
    protected T mView;
    protected Subscription mSubscription;


    @Override
    public void attachView(@NonNull BaseContract.View view) {
        mView = (T) view;
    }

    @Override
    public void onDestroy() {
        MyUtils.cancelSubscription(mSubscription);
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }

}
