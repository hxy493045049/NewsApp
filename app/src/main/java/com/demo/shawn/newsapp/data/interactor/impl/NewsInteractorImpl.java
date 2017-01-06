package com.demo.shawn.newsapp.data.interactor.impl;

import com.demo.shawn.newsapp.data.RequestCallBack;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.data.interactor.NewsInteractor;

import java.util.List;

import javax.inject.Inject;

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
    public Subscription loadNewsChannels(RequestCallBack<List<NewsChannel>> callBack) {

        return null;
    }
}
