package com.demo.shawn.newsapp.data.interactor.impl;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.data.RequestCallBack;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.data.db.manager.NewsChannelManager;
import com.demo.shawn.newsapp.data.interactor.NewsInteractor;
import com.demo.shawn.newsapp.utils.TransformUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */

public class NewsInteractorImpl implements NewsInteractor<List<NewsChannel>> {
    @Inject
    public NewsInteractorImpl() {
    }

    @Override
    public Subscription loadNewsChannels(final RequestCallBack<List<NewsChannel>> callBack) {

        return Observable.create(new Observable.OnSubscribe<List<NewsChannel>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannel>> subscriber) {
                NewsChannelManager.initNewsChannels();
                subscriber.onNext(NewsChannelManager.loadNewsChannelsMine());
                subscriber.onCompleted();
            }
        }).compose(TransformUtils.<List<NewsChannel>>defaultSchedulers())
                .subscribe(new Subscriber<List<NewsChannel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(App.getAppContext().getString(R.string.db_error));
                    }

                    @Override
                    public void onNext(List<NewsChannel> newsChannels) {
                        callBack.success(newsChannels);
                    }
                });
    }
}
