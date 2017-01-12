package com.demo.shawn.newsapp.module.contract.impl.presenter;

import com.demo.shawn.newsapp.base.impl.BasePresenterImpl;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.data.interactor.NewsInteractor;
import com.demo.shawn.newsapp.module.contract.NewsContract;
import com.demo.shawn.newsapp.module.contract.impl.view.NewsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsView, List<NewsChannel>>
        implements NewsContract.Presenter {

    private NewsInteractor<List<NewsChannel>> mNewsInteractor;

    @Inject
    public NewPresenterImpl(NewsInteractor<List<NewsChannel>> newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        loadNewsChannels();
    }

    private void loadNewsChannels() {
        mSubscription = mNewsInteractor.loadNewsChannels(this);
    }

    @Override
    public void success(List<NewsChannel> data) {
        super.success(data);
        mView.initViewPager(data);
    }

    @Override
    public void onChannelDbChanged() {
        loadNewsChannels();
    }
}
