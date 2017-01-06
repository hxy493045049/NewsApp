package com.demo.shawn.newsapp.data.interactor;

import com.demo.shawn.newsapp.data.RequestCallBack;

import rx.Subscription;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */

public interface NewsInteractor<T> {
    Subscription loadNewsChannels(RequestCallBack<T> callBack);
}
